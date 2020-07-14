/* ====================================== */
/* Copyright (c) 2004 Unisys Corporation. */
/*          All rights reserved.          */
/*          UNISYS CONFIDENTIAL           */
/* ====================================== */

//Source file: W:\\aircore\\src\\com\\unisys\\trans\\aircore\\bkg\\bookingservice\\BKGLeg.java

package com.unisys.trans.aircore.bkg.bookingservice;

import com.unisys.trans.aircore.bkg.constant.BKGConstants;
import com.unisys.trans.aircore.bkg.constant.BKGReasonCodeConstants;
import com.unisys.trans.aircore.bkg.param.AllotmentListParam;

import com.unisys.trans.aircore.utils.client.SecurityParam;
import com.unisys.trans.shared.util.date.SharedDate;
import com.unisys.trans.shared.util.response.SharedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

import java.util.List;
import java.util.StringTokenizer;

/**
 * This class is the business object for the leg of a flight.
 *
 * @author Unisys
 * @version 1
 * @since 4-1-03
 */
public class BKGLeg {
    /**
     * The type of aircraft used for this leg
     */
    private String aircraft;

    /**
     * List of compartments in this leg
     */
    private ArrayList bkgCompartmentArrayList;

    /**
     * attribute to indicate boarding time
     */
    private String boardingTime;

    /**
     * The departure date for this leg
     */
    private SharedDate departureDate;

    /**
     * the departure gate of this leg
     */
    private String departureGate;

    /**
     * the departure time of this leg
     */
    private String departureTime;


    /**
     * The destination of this leg
     */
    private String destination;

    /**
     * The origin of this leg
     */
    private String origin;
    /**
    * List of marketing leg for this complex operating flight
     */
   private List<BKGFlight> marketingFlightList;

    /**
     * The meal of this leg
     */
     private String meals;

    /**
     * The mealService note from the flight details.
     */
     private String mealNote ;
    /**
     * Operating flight for the complex marketing flight
     */
    private BKGFlight operatingFlight;


    /**
      * Holds the list of allotment list details.
      * <p>
      * It contains the <code>allotmentList</code> which has the
      * all the details of an allotment.
     */
    private List allotmentList;

    private String status;
   
    /**
     * Holds the secure flight data information.
     * <p>
     */
    private String secureFlightData;    

    /**
     * Default Constructor.
     * @roseuid 3E89AE06005A
     */
    public BKGLeg() {

    }


    /**
     * This method builds not only the LegParam, but also builds
     * the params for all of the attached business classes.
     *
     * @return LegParam
     * @roseuid 3E89A72102BC
     */
    public LegParam buildParams() throws SharedException {
        LegParam aLegParam = new LegParam();
        aLegParam.setAircraft(this.aircraft);

        if (this.getDepartureDate() != null && !this.getDepartureDate().equals("")) {
            aLegParam.setDepartureDate(this.getDepartureDate().getDateString());
        }
        aLegParam.setDestination(this.destination);
        aLegParam.setOrigin(this.origin);
        aLegParam.setDepartureGate(this.getDepartureGate());
        aLegParam.setEstimatedDepartureTime(this.getDepartureTime());
        aLegParam.setBoardingTime(this.getBoardingTime());
        aLegParam.setMealServiceNote(this.getMeals());
        // Build the Params for each of the compartments in the leg
        int iMax = this.getBkgCompartmentArrayList().size();
        for (int i = 0; i < iMax; ++i) {
            aLegParam.getCompartmentParamArrayList().add(
                ((BKGCompartment)this.getBkgCompartmentArrayList().get(i)).buildParams());
        }

        final int jMax = this.getAllotmentList().size();
        for (int j = 0; j < jMax; j++) {
            aLegParam.getAllotmentListParamsList().add(
                ((AllotmentList)this.getAllotmentList().get(j)).buildParams());

        }
        if (this.getOperatingFlight() != null) {
        	BKGFlightParam aBKGFlightParam = new BKGFlightParam();
        	aBKGFlightParam.setAirlineDesignator(this.getOperatingFlight().getAirlineDesignator());
        	aBKGFlightParam.setFlightNumber(this.getOperatingFlight().getFlightNumber());
        	aBKGFlightParam.setFlightSuffix(this.getOperatingFlight().getFlightSuffix());
        	aLegParam.setOperatingFlightParam(aBKGFlightParam);
        }
        
        List<BKGFlight> aBKGFlightList = this.getMarketingFlightList();
        if (aBKGFlightList.size() > 0) {
        	for (BKGFlight aBKGFlight : aBKGFlightList) {
            	BKGFlightParam aBKGFlightParam = new BKGFlightParam();
            	aBKGFlightParam.setAirlineDesignator(aBKGFlight.getAirlineDesignator());
            	aBKGFlightParam.setFlightNumber(aBKGFlight.getFlightNumber());
            	aBKGFlightParam.setFlightSuffix(aBKGFlight.getFlightSuffix());
            	aBKGFlightParam.setFlightDate(aBKGFlight.getFlightDate().getDateString());
           		aLegParam.addMarketingFlightParam(aBKGFlightParam);
        	}
        }
        aLegParam.setStatus(this.getStatus());
        return aLegParam;
    }


    /**
     * This method checks to see if the leg passed in has the same seat configuration
     *
     * @param pBKGLeg
     * @return boolean
     */
    public boolean hasSameConfiguration(BKGLeg pBKGLeg) {
        boolean hasSameConfiguration = false;
        // first check if the aircraft type is the same
        if (this.aircraft != null && pBKGLeg.aircraft!= null && this.aircraft.equals(pBKGLeg.aircraft)) {
            BKGCompartment aCompartment;
            BKGCompartment bCompartment;
            ArrayList bCompartmentArrayList = (ArrayList)pBKGLeg.getBkgCompartmentArrayList().clone();
            for (int i = this.getBkgCompartmentArrayList().size()-1; i > -1; i--) {
                hasSameConfiguration = false;
                aCompartment = ((BKGCompartment)this.getBkgCompartmentArrayList().get(i));
                // see if this compartment matches any of the compartments in pBKGLeg
                for (int j = bCompartmentArrayList.size()-1; j > -1; j--) {
                    bCompartment = (BKGCompartment)bCompartmentArrayList.get(j);
                    if (aCompartment.getCompartmentDesignator().equals(bCompartment.getCompartmentDesignator()) &&
                        aCompartment.getPhysicalCapacity() == bCompartment.getPhysicalCapacity()) {
                        // the compartments are equal, remove from bCompartmentArrayList and break the loop
                        bCompartmentArrayList.remove(j);
                        hasSameConfiguration = true;
                        break;
                    }
                }
                // if a matching compartment is not found . . .
                if (!hasSameConfiguration) {
                    break;
                }
            }
            if (!bCompartmentArrayList.isEmpty()) {
                // we exhausted the list of compartments in THIS leg but not in the leg defined by the parameter
                hasSameConfiguration = false;
            }
        }
        return hasSameConfiguration;
    }


    /**
     * This method checks to see if the station passed in is the same
     * as the destination of this leg.
     *
     * @param pStation
     * @return boolean
     */
    public boolean isDestinationEqual(String pStation) {
        if (this.getDestination().equals(pStation)) {
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * This method materializes the LegParam into the business class.
     * It also materializes all of the attached business classes.
     *
     * @param pLegParam
     * @roseuid 3E89A7280009
     */
    public void materialize(LegParam pLegParam) throws SharedException {
        this.setAircraft(pLegParam.getAircraft());
        if (pLegParam.getDepartureDate() != null) {
            this.setDepartureDate(new SharedDate(pLegParam.getDepartureDate()));
        }
        this.setDestination(pLegParam.getDestination());
        this.setOrigin(pLegParam.getOrigin());
        this.setDepartureGate(pLegParam.getDepartureGate());
        this.setDepartureTime(pLegParam.getEstimatedDepartureTime());
        this.setBoardingTime(pLegParam.getBoardingTime());
        this.setMeals(pLegParam.getMealServiceNote());

        // Materialize each of the compartments and add to the bkgCompartmentArrayList
        BKGCompartment aBKGComp = null;
        final int iMax = pLegParam.getCompartmentParamArrayList().size();
        for (int i = 0; i < iMax; i++) {
            aBKGComp = new BKGCompartment();
            aBKGComp.materialize((CompartmentParam)pLegParam.getCompartmentParamArrayList().get(i));
            this.getBkgCompartmentArrayList().add(aBKGComp);
        }

        final int jMax = pLegParam.getAllotmentListParamsList().size();
        AllotmentList anAllotmentList;
        for(int j=0; j<jMax; j++){
            anAllotmentList = new AllotmentList();
            this.getAllotmentList().add(anAllotmentList);
            anAllotmentList.materialize((AllotmentListParam)pLegParam.getAllotmentListParamsList().get(j));
        }
        if(pLegParam.getOperatingFlightParam() != null) {
        	BKGFlightParam aBKGFlightParam = pLegParam.getOperatingFlightParam();
        	BKGFlight aBKGFlight = new BKGFlight();
        	aBKGFlight.setAirlineDesignator(aBKGFlightParam.getAirlineDesignator());
        	aBKGFlight.setFlightNumber(aBKGFlightParam.getFlightNumber());
        	aBKGFlight.setFlightSuffix(aBKGFlightParam.getFlightSuffix());
        	this.setOperatingFlight(aBKGFlight);        	
        }
        
        List<BKGFlightParam> aMarketingFlightParamList = pLegParam.getMarketingFlightParamList();
        if (aMarketingFlightParamList.size() > 0) {
        	for (BKGFlightParam aBKGFlightParam : aMarketingFlightParamList) {
            	BKGFlight aBKGFlight = new BKGFlight();
            	aBKGFlight.setAirlineDesignator(aBKGFlightParam.getAirlineDesignator());
            	aBKGFlight.setFlightNumber(aBKGFlightParam.getFlightNumber());
            	aBKGFlight.setFlightSuffix(aBKGFlightParam.getFlightSuffix());
            	aBKGFlight.setFlightDate(new SharedDate(aBKGFlightParam.getFlightDate()));
            	this.addMarketingFlight(aBKGFlight);
        	}
        }
        this.setStatus(pLegParam.getStatus());
        this.setDepartureGate(pLegParam.getDepartureGate());
        this.setSecureFlightData(pLegParam.getSecureFlightData());
    }


    /**
     * This method is used to generate a Consolidated or EOT ADL list.  It filters through a list of bookings
     * that are to be included on the ADL and places each in the appropriate leg.
     * NOTE: On the ADL, the bookings on a leg are really those booked from a specific boarding point
     *       on the flight to the leg's destination.
     *
     * @param pPNLADLList
     * @param pSecurityParam
     * @param pHistoryArrayList - ArrayList of history records for the bookings with specific changes that
     *                            are to be included on the ADL.
     */
    public void filterBookingsInLegForADL(PNLADLList pPNLADLList, SecurityParam pSecurityParam,
                                          ArrayList pHistoryArrayList) throws SharedException {
        BKGCompartment aBKGCompartment;
        ArrayList aWorkingTripArrayList;

        // Retrieve a list of all the bookings if we will need to calculate PAD counts later
        if (pPNLADLList.getIncludePAD()) {
            aWorkingTripArrayList = this.retrieveBookingsOnFlightForPNLADL(pPNLADLList, pSecurityParam);
        }
        else {
            aWorkingTripArrayList = null;
        }

        // Filter through the list of bookings, adding each to it's appropriate compartment and class
        final int iMax = this.getBkgCompartmentArrayList().size();
        for (int i = 0; i < iMax; i++) {
            aBKGCompartment = (BKGCompartment)this.getBkgCompartmentArrayList().get(i);

            aBKGCompartment.filterBookingsInCompartmentForADL(pPNLADLList, pHistoryArrayList,
                                                              aWorkingTripArrayList, this.destination,
                                                              pSecurityParam);
        }
    }


    /**
     * This method is used to generate a PNL list.  It retrieves a list of the bookings,
     * and filters through them placing each in the appropriate leg.
     * NOTE: On the PNL, the bookings on a leg are really those booked from a specific boarding point
     *       on the flight to the leg's destination.
     *
     * @param pPNLADLList
     * @param pSecurityParam
     */
    public void retrieveBookingsInLegForPNL(PNLADLList pPNLADLList,
                                            SecurityParam pSecurityParam) throws SharedException {
        BKGCompartment aBKGCompartment;
        ArrayList aWorkingTripArrayList;

        // Retrieve a list of the bookings on the flight segment (boarding point/destination)
        aWorkingTripArrayList = this.retrieveBookingsOnFlightForPNLADL(pPNLADLList, pSecurityParam);

        if (aWorkingTripArrayList != null && aWorkingTripArrayList.size() > 0) {
            // Filter through the list of bookings, adding each to it's appropriate compartment and class
            final int kMax = this.getBkgCompartmentArrayList().size();
            for (int k = 0; k < kMax; k++) {
                aBKGCompartment = (BKGCompartment)this.getBkgCompartmentArrayList().get(k);

                aBKGCompartment.filterBookingsInCompartmentForPNL(pPNLADLList,
                                                                  this.destination,
                                                                  aWorkingTripArrayList);
            }
        }
    }

    /**
     * This Method is to construct the Meal Service String that has to be parsed
     * to match the class in the meal note , to which compartment it belongs to
     * and display the type of meal in that compartment.
     */
    public String buildMealServiceString(String pMealServiceNote){
     String mealNoteService ;
     String mealService = pMealServiceNote;
      if (mealService.lastIndexOf("//") != -1) {
           int index = mealService.lastIndexOf("//");
           mealNoteService = mealService.substring(0, index);
      }
      else {
           mealNoteService = mealService;
      }
      return mealNoteService;
  }


  /**
   * Builds the map by parsing the meal service note and the class will be matched with
   * the corresponding compartment.
   */
  public Map parseMealNoteAndMatchClass(String pMealServiceNote, Map pCompartmentClassMap){
  String strToBetokened = this.buildMealServiceString(pMealServiceNote);
  StringTokenizer aStringTokenizer = new StringTokenizer(strToBetokened, "/");
  Map aCompartmentClassMealMap = new HashMap();
  Map aClassMealMap;

  while(aStringTokenizer.hasMoreElements())
        {
            List mealList = new ArrayList();
            this.mealNote = aStringTokenizer.nextToken();
            int subLen = this.mealNote.length();
            char aClass = this.mealNote.charAt(0);
            String aTypecastedClass = new String( new char[]{aClass});
            String mealsOfClass = this.mealNote.substring(1,subLen);
            if(mealsOfClass!=null && mealsOfClass.length() >0){

                char meal1 = mealsOfClass.charAt(0);
                mealList.add(new Character(meal1));
                if(mealsOfClass.length () > 1 && mealsOfClass.charAt(1) != ' ' ){
                char meal2 = mealsOfClass.charAt(1);
                mealList.add(new Character(meal2));
                  }
              }

              Map aCompartmentClassMap ;
              aCompartmentClassMap = pCompartmentClassMap;
              Set aCompartmentClassMapSet = aCompartmentClassMap.entrySet();
              Iterator aCompartmentClassMapIterator = aCompartmentClassMapSet.iterator();
              String aCompartment ;
              StringBuffer classDesignators;
                 while(aCompartmentClassMapIterator.hasNext()){
                 Map.Entry anEntry = (Map.Entry)aCompartmentClassMapIterator.next();
                 aCompartment =(String) anEntry.getKey();
                 classDesignators = (StringBuffer)anEntry.getValue();
                 if(classDesignators.indexOf(aTypecastedClass) >= 0 ){

                 aClassMealMap = (Map)aCompartmentClassMealMap.get(aCompartment);
                 if(aClassMealMap == null) {
                    aClassMealMap = new HashMap();
                    aCompartmentClassMealMap.put(aCompartment,aClassMealMap);
                 }
                 aClassMealMap.put(aTypecastedClass,mealList);

                 }

             }

       }
   return aCompartmentClassMealMap;
  }

    // ************************************************************************
    //                            Private Methods
    // ************************************************************************

    /**
     * This method retrieves the bookings, for a specific boarding point
     * on the flight to the destination of this leg.
     *
     * @param pPNLADLList
     * @param pSecurityParam
     */
    private ArrayList retrieveBookingsOnFlightForPNLADL(PNLADLList pPNLADLList,
                                                        SecurityParam pSecurityParam) throws SharedException {
        PermBooking aPermBooking;
        TripParams aTripParam;
        WorkingTrip aWorkingTrip;
        ArrayList aWorkingTripArrayList = new ArrayList();

        // Retrieve a list of the Trip Id's for the bookings with segments for
        // the boarding airport to this leg's destination
        aPermBooking = new PermBooking();
        TripResult aResult = aPermBooking.listPermanentTrips(
                             new WorkingTripSegment(pPNLADLList, this));


        // Retrieve list of Trip IDs for codeshare bookings
        TripResult bResult = aPermBooking.listPermanentTripsForMarketing(
                new WorkingTripSegment(pPNLADLList, this));
            String aTripId;
            ArrayList aTripIdArrayList = new ArrayList();
        if (aResult != null && aResult.isStatusSuccessful() && aResult.getTripParamsArrayList().size() > 0) {
             //Create a list of the unique trip ID's to use in the next search
            final int iMax = aResult.getTripParamsArrayList().size();
            int aPlanStatus;  
            for (int i = 0; i < iMax; i++) {
                aPlanStatus = ((TripParams)aResult.getTripParamsArrayList().get(i)).getPlanStatus();
                if (aPlanStatus != BKGConstants.CANCELLED){             
                aTripId = ((TripParams)aResult.getTripParamsArrayList().get(i)).getTripId();

                // Add the Trip ID to the ArrayList if it does not yet exist
                if (aTripIdArrayList.indexOf(aTripId) == -1) {
                    aTripIdArrayList.add(aTripId);
                }
                }
            }
        }

        if (bResult != null && bResult.isStatusSuccessful() && bResult.getTripParamsArrayList().size() > 0) {
            // Create a list of the unique trip ID's to use in the next search
            final int kMax = bResult.getTripParamsArrayList().size();
            int bPlanStatus;
            for (int k = 0; k < kMax; k++) {
                 bPlanStatus = ((TripParams)bResult.getTripParamsArrayList().get(k)).getPlanStatus();
                 if (bPlanStatus != BKGConstants.CANCELLED){
                 aTripId = ((TripParams)bResult.getTripParamsArrayList().get(k)).getTripId();

                 // Add the Trip ID to the ArrayList if it does not yet exist
                 if (aTripIdArrayList.indexOf(aTripId) == -1) {
                        aTripIdArrayList.add(aTripId);
                    }
                 } 
            }

        }

        if(aTripIdArrayList.size() > 0 ){
            // Retrieve the booking details for each Trip Id
            for (int j = 0; j < aTripIdArrayList.size(); j++) {
                aTripParam = new TripParams();
                aTripParam.setTripId((String) aTripIdArrayList.get(j));
                aTripParam.setSecurityParam(pSecurityParam);


                // Added the original method call back on 11-7 because the readBookingForPNL method
                // was not working
                try {
                       aWorkingTrip = aPermBooking.readBookingByTripId(aTripParam);
                }
                catch (SharedException se) {
                    if (se.getResponseData().getReasonCode() ==
                            BKGReasonCodeConstants.DEPART_TRIP_ACCESS_DENIED ||
                            se.getResponseData().getReasonCode() ==
                            BKGReasonCodeConstants.SEGMENT_ON_EMERGENCY_LOCK) {
                        aTripIdArrayList.remove(j);
                        // Since removal of the trip will alter the list count,
                        // decrement the loop counter to keep in sync with the List.
                        j--;
                        continue;
                    }
                    else {
                        throw se;
                    }
                }
                // Add the booking to the ArrayList
                aWorkingTripArrayList.add(aWorkingTrip);

            }

        }

        return aWorkingTripArrayList;
    }

    /**
     * This method returns back a workingtrip
     * which has cancelled customers .
     *
     *
     * @param pWorkingTrip
     * @param pList
     */

    //This method takes two arguments pWorkingTrip and List pList which consists of cancelled passengers ,we should return the working trip which has same tripid as the otherparameter pWorkingTrip
   public WorkingTrip findWorkingTrip(WorkingTrip pWorkingTrip,List pList)
    {

        for(int i=0;i<pList.size();i++)
        {
            WorkingTrip trip=(WorkingTrip)pList.get(i);
            if(trip.getTripId().equals(pWorkingTrip.getTripId()))
            {
        return trip;
            }

        }
        //if the trip id in pWorkingTrip does not has any cancelled passengers then return a null
        return null;
    }



    // ************************************************************************
    //                                  Getters
    // ************************************************************************

    /**
     * @return String
     * @roseuid 3E89A733020E
     */
    public String getAircraft() {
        return this.aircraft;
    }


    /**
     * @return ArrayList
     * @roseuid 3E89A73602F9
     */
    public ArrayList getBkgCompartmentArrayList() {
        if (this.bkgCompartmentArrayList == null) {
            this.bkgCompartmentArrayList = new ArrayList();
        }
        return this.bkgCompartmentArrayList;
    }

    /**
     * access method for boarding time
     * @return String
     */
    public String getBoardingTime () {
        return this.boardingTime;
    }

    /**
     * @return SharedDate
     */
    public SharedDate getDepartureDate() {
        return this.departureDate;
    }

    /**
     * access method for departure gate
     * @return String
     */
    public String getDepartureGate() {
        return this.departureGate;
    }


    /**
     * access method for departure gate
     * @return String
     */
    public String getDepartureTime() {
        return this.departureTime;
    }


    /**
     * @return String
     * @roseuid 3E89A72B01A8
     */
    public String getDestination() {
        return this.destination;
    }

    /**
     * Returns the operating BKGLeg for this complex operating flight
     */
    public BKGFlight getOperatingFlight() {
    	return this.operatingFlight;
    }

    /**
     * @return String
     * @roseuid 3E89A73000A1
     */
    public String getOrigin() {
        return this.origin;
    }

    /**
     * This method is used to get the meal.
     * @return String- the meal of the current BKGLeg object.
     */
    public String getMeals() {
            return this.meals;
        }
    
    /**
     * Returns a list of BKGLeg objects that holds marketing leg information
     * for this leg of operating complex flight. 
     */
    public List<BKGFlight> getMarketingFlightList() {
    	if (this.marketingFlightList == null) {
    		this.marketingFlightList = new ArrayList<BKGFlight>();
    	}
    	return this.marketingFlightList;
    }



    // ************************************************************************
    //                                  Setters
    // ************************************************************************

    /**
     * @param pAircraft
     * @roseuid 3E89A74100F6
     */
    public void setAircraft(String pAircraft) {
        this.aircraft = pAircraft;
    }


    /**
     * @param pCompartmentArrayList
     * @roseuid 3E89A7440173
     */
    public void setBkgCompartmentArrayList(ArrayList pCompartmentArrayList) {
        this.bkgCompartmentArrayList = pCompartmentArrayList;
    }


    /**
     * sets the value of boarding time
     * @param pBoardingTime
     */
    public void setBoardingTime(String pBoardingTime) {
        this.boardingTime = pBoardingTime;
    }

    /**
     * @param pDepartureDate
     */
    public void setDepartureDate(SharedDate pDepartureDate) {
        this.departureDate = pDepartureDate;
    }

    /**
     * set the value of departure gate
     * @param pDepartureGate
     */
    public void setDepartureGate (String pDepartureGate) {
        this.departureGate = pDepartureGate;
    }

    /**
     * set the value of departure gate
     * @param pDepartureTime
     */
    public void setDepartureTime (String pDepartureTime) {
        this.departureTime = pDepartureTime;
    }


    /**
     * @param pDestination
     * @roseuid 3E89A73B02D8
     */
    public void setDestination(String pDestination) {
        this.destination = pDestination;
    }
    /**
    * Sets the operating BKGLeg for this complex operating BKGLeg
    */
   public void setOperatingFlight(BKGFlight pFlight) {
   	this.operatingFlight = pFlight;
   }


    /**
     * @param pOrigin
     * @roseuid 3E89A73E0340
     */
    public void setOrigin(String pOrigin) {
        this.origin = pOrigin;
    }

    /**
     * This method is used to set the Meal.
     * @param pMeals
     */
    public void setMeals(String pMeals) {
       this.meals = pMeals;
    }
    
   /**
    * Adds BKGLeg to the marketing leg list.
    */
   public void addMarketingFlight(BKGFlight pFlight) {
   	this.getMarketingFlightList().add(pFlight);
   }

    /**
     * Gets the list of the allotmentlist.
     *
     * @return List - The value of <code>allotmentListParamsList</code>
     * property.
     */
    public List getAllotmentList() {
        if(this.allotmentList == null){
            this.allotmentList = new ArrayList();
        }
        return this.allotmentList;
    }

    /**
     * Sets the value of the list of allotmentlists.
     *
     * @param pAllotmentList The new List of
     * <code>allotmentListParamsList</code> objects.
     */
    public void setAllotmentList(List pAllotmentList) {
        this.allotmentList = pAllotmentList;
    }

    /**
     * Gets the value of the status.     *
     *
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Sets the value of the status.     *
     *
     */
    public void setStatus(String pStatus) {
        this.status = pStatus;
    }

    /**
     * Returns the secure flight data information of BKGLeg.
     *
     * @return Returns the secureFlightData.
     */
    public String getSecureFlightData() {
        return this.secureFlightData;
    }

    /**
     * Sets the secure flight data in BKGLeg.
     *
     * @param secureFlightData The secureFlightData to set.
     */
    public void setSecureFlightData(String secureFlightData) {
        this.secureFlightData = secureFlightData;
    }
}
