/* ====================================== */
/* Copyright (c) 2004 Unisys Corporation. */
/*          All rights reserved.          */
/*          UNISYS CONFIDENTIAL           */
/* ====================================== */

//Source file: Z:\\aircore\\src\\com\\unisys\\trans\\aircore\\bkg\\bookingservice\\BaseTrip.java

package com.unisys.trans.aircore.bkg.bookingservice;

import com.unisys.trans.shared.util.date.SharedDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This is the base class that the working trip and trip inherit from. It contains
 * all the
 * gets and sets for their common data attributes.
 * @author Unisys
 * @version 1
 * @since 11-14-2000
 */

public class BaseTrip extends BaseBooking {


    /**
     * The remarkId number of the first remark for this booking.
     */
    private long remarkId;
    /**
     * The contactId number of the first contact for this booking.
     */
    private long contactId;

    /**
     * The queueRequestId number of the first queue request for this booking.
     */
    private long queueRequestId;

    /**
     * The mailingId number of the first mailing for this booking.
     */
    private long mailingId;

    /**
     * The ssrId number of the first trip ssr for this booking.
     */
    private long ssrId;

    /**
     * The ssrId number of the first TEMPORARY trip ssr for this booking.
     */
    private long tempSSRId;

   /**
    * To retain the Eot Time in the Trip, since the eotTime attribute in the
    * BaseTrip object is overwritten by the eotTime from TripExtenison.
    */
    private long tripEotTime;

    /**
     * The number of seats(spaces) allocated by IMC for each segment of this booking.
     */
    private int numberOfSeats;

    /**
     * The record locator of the requesting owner of this booking.
     *
     * If the booking is owned by the host, then the value set is null.
     * If the booking is owned by non host airline, then the value set is
     * the trip id of the non host airline.
     */
    private String ownerTripId;

    /**
     * This booking was created from a no match situation, this is the tripId that
     * was searched for.
     */
    public String noMatchTripId;
    /**
     * This booking was created from a divided booking, this is the trip Id of the
     * original booking.
     */
    public String divideFromTripId;

    
    /**
     * It is used to hold list of medif
     */
    public ArrayList medifArrayList;


	/**
     * This booking was divided, this is the trip Id of the
     * new booking.
     */
    public String divideToTripId;

    /**
     * Identifies the claimServicePNR status
     */
    private String claimServicePNRStatus;

    /**
     * An ArrayList of remark objects for this trip.
     */
    private ArrayList remarkArrayList;

    /**
     * An ArrayList of contact objects for this trip.
     */
    private ArrayList contactArrayList;

    /**
     * An ArrayList of queuerequest objects for this trip.
     */
    private ArrayList queueRequestArrayList;

    /**
     * An ArrayList of mailing objects for this trip.
     */
    private ArrayList mailingArrayList;

    /**
     * An ArrayList of ssr objects for this trip.
     */
    private ArrayList ssrArrayList;

    /**
     * The group name for this booking, or null if not a group.
     */
    private String groupName;

    /**
     * the airline owner that owns the booking
     */
    private String airlineOwner;

    /**
     * the agent or customer that created the booking
     */
    private String bookingOwner;

    /**
     * Contains the earliest booking scan date
     */
    private SharedDate bkgScanDate;

    /**
     * Identifies the source of this update request.
     * Values contained in PermPersistTags. (WEB_UPDATE, TTY_UPDATE)
     */
    private int updateSource;
    /**
     * Identifies the Security name that Eot'ed this booking.
     */
    private String updateName;
    /**
     * Identifies the location that Eot'ed this booking.
     */
    private String updateOffice;
    /**
     * Identifies the agentAirlineDesignator that Eot'ed this booking.
     */
    private String updateAirlineDesignator;
    /**
     * XML string representing the entire booking
     */
    private String xmlString;
    /**
     * flag used for product testing
     */
    private boolean useNeoCore;

    /**
     * status of this plan, PermPersistTags.CANCELLED or PermPersistTags.ACTIVE
     */
    private int planStatus;
    /**
     * Contains a historyId which identifies a previous version of this trip if one exists.
     */
    private long historyId;
    /**
     * Contains an eotTime of this trip.
     */
    private long eotTime;
    /**
     * Contains the update sequence count of this trip.
     */
    private int updateCount;

    /**
     * This holds the value of the primary record locator from the Incoming
     * Teletype message which may be ownerTripId or providerTripId.
     *
     * The foreignTripParamList holds the ownerTripId (trip id of non host airline),
     * non host airline designator,
     * when the booking is owned by non host airline and the host owns one or
     * more segments in that booking
     */
    private List foreignTripParamList;

    /**
     * This flag will be set if a new record locator exists
     */
    private boolean isNewRecordLocator;

    /**
     * Attribute to hold Session Index value
     */
    private int mqSessionIndex = -1;

    /**
     * This holds the original message string of Incoming Teletype message
     */

    private String originalMessage;
    /**
     * This holds the office code
     */
    private String officeCode;

    /**
     * This holds the agent id
     */
    private String agentId;

    /**
     * This holds the uniqueMessageIdentifier
     */
    private String uniqueMessageIdentifier;

    /**
     * This holds the reactivatedFromTripId
     */
    private String reactivatedFromTripId;

    /**
     * Constructor.
     * @roseuid 3C83A4380380
     */
    protected BaseTrip() {

    }


    /**
     * @return int
     * @roseuid 3C83A43803BC
     */
    public int getNumberOfSeats() {
        return this.numberOfSeats;
    }

    /**
     * @param pNewValue
     * @roseuid 3C83A43803D0
     */
    public void setNumberOfSeats(int pNewValue) {
        this.numberOfSeats = pNewValue;
    }

    public long getRemarkId() {
        return this.remarkId;
    }

    public void setRemarkId(long pId) {
        this.remarkId = pId;
    }

    public long getContactId() {
        return this.contactId;
    }

    public long getQueueRequestId() {
        return this.queueRequestId;
    }

    public void setContactId(long pId) {
        this.contactId = pId;
    }

    public void setQueueRequestId(long pId) {
        this.queueRequestId = pId;
    }

    public long getMailingId() {
        return this.mailingId;
    }

    public void setMailingId(long pId) {
        this.mailingId = pId;
    }

    public long getSsrId() {
        return this.ssrId;
    }

    public void setSsrId(long pId) {
        this.ssrId = pId;
    }

    public long getTempSsrId() {
        return this.tempSSRId;
    }

    public void setTempSsrId(long pId) {
        this.tempSSRId = pId;
    }


    /**
     * @return String
     * @roseuid 3C83A4390042
     */
    public String getBookingOwner() {
        return this.bookingOwner;
    }

    /**
     * @param pNewValue
     * @roseuid 3C83A4390056
     */
    public void setBookingOwner(String pNewValue) {
        this.bookingOwner = pNewValue;
    }

    public ArrayList getRemarkArrayList() {
        if (this.remarkArrayList == null) {
            this.remarkArrayList = new ArrayList();
        }
        return(this.remarkArrayList);
    }

    public ArrayList getContactArrayList() {
        if (this.contactArrayList == null) {
            this.contactArrayList = new ArrayList();
        }
        return this.contactArrayList;
    }


    public ArrayList getQueueRequestsArrayList() {
       if (this.queueRequestArrayList == null) {
           this.queueRequestArrayList = new ArrayList();
       }
       return this.queueRequestArrayList;
   }



    public ArrayList getMailingArrayList() {
        if (this.mailingArrayList == null) {
            this.mailingArrayList = new ArrayList();
        }
        return this.mailingArrayList;
    }



    public ArrayList getSsrArrayList() {
        if (this.ssrArrayList == null) {
            this.ssrArrayList = new ArrayList();
        }
        return(this.ssrArrayList);
    }

    public void setWorkingSsrArrayList(ArrayList pArrayList) {

        this.ssrArrayList = pArrayList;
    }


    /**
     * This method gets the earliest booking scan date
     */
    public SharedDate getBKGScanDate() {
        return this.bkgScanDate;
    }



    /**
     * This method sets the earliest booking scan date
     */
    public void setBkgScanDate(final SharedDate pNewValue) {
        this.bkgScanDate = pNewValue;
    }

    /**
     * returns the source of update request.
     * @return int
     */
    public int getUpdateSource() {
        return this.updateSource;
    }

    /**
     * Identifies the source of update request.
     * @param pNewValue
     *
     */
    public void setUpdateSource(int pNewValue) {
        this.updateSource = pNewValue;
    }
    /**
     * returns the SecurityParams Name of update request.
     * @return String
     */
    public String getUpdateName() {
        return this.updateName;
    }

    /**
     * Identifies the SecurityParams Name of update request.
     * @param pNewValue
     *
     */
    public void setUpdateName(String pNewValue) {
        this.updateName = pNewValue;
    }

    /**
     * returns the SecurityParams agentAirlineDesginator of update request.
     * @return String
     */
    public String getUpdateAirlineDesignator() {
        return this.updateAirlineDesignator;
    }

    /**
     * Sets the SecurityParams agentAirlineDesginator of update request into this object.
     * @param pNewValue
     *
     */
    public void setUpdateAirlineDesignator(String pNewValue) {
        this.updateAirlineDesignator = pNewValue;
    }

    /**
     * returns the Office of update request.
     * @return String
     */
    public String getUpdateOffice() {
        return this.updateOffice;
    }

    /**
     * This method returns the value of claimServicePNRStatus
     * @return String
     */
    public String getClaimServicePNRStatus() {
        return this.claimServicePNRStatus;
    }

    /**
     * This method sets the value of claimServicePNRStatus
     * @param pNewValue
     */
    public void setClaimServicePNRStatus(String pNewValue) {
        this.claimServicePNRStatus = pNewValue;
    }

    /**
     * Identifies the Office of update request.
     * @param pNewValue
     *
     */
    public void setUpdateOffice(String pNewValue) {
        this.updateOffice = pNewValue;
    }

    /**
     * @return String
     * @roseuid 3C83A439006A
     */
    public String getXmlString() {
        return this.xmlString;
    }

    /**
     * @param pNewValue
     * @roseuid 3C83A439007E
     */
    public void setXmlString(String pNewValue) {
        this.xmlString = pNewValue;
    }

    /**
     * @return boolean
     * @roseuid 3C83A4390092
     */
    public boolean getUseNeoCore() {
        return this.useNeoCore;
    }

    /**
     * @param pNewValue
     * @roseuid 3C83A43900A6
     */
    public void setUseNeoCore(boolean pNewValue) {
        this.useNeoCore = pNewValue;
    }

    public int getPlanStatus(){
        return(this.planStatus);
    }

    public void setPlanStatus(int pValue){
        this.planStatus = pValue;
    }

    public long getHistoryId(){
        return this.historyId;
    }

    public void setHistoryId(long pHistoryId){
        this.historyId = pHistoryId;
    }

    public long getEotTime(){
        return this.eotTime;
    }

    public void setEotTime(long pEotTime){
        this.eotTime = pEotTime;
    }

    public int getUpdateCount(){
        return this.updateCount;
    }

    public void setUpdateCount(int pUpdateCount){
        this.updateCount = pUpdateCount;
    }

    public void setOwnerTripId(String pNewValue) {
        this.ownerTripId = pNewValue;
    }

    public String getOwnerTripId() {
        return(this.ownerTripId);
    }

    public void setGroupName(String pNewValue) {
        this.groupName = pNewValue;
    }

    public String getGroupName() {
        return(this.groupName);
    }

    public void setNoMatchTripId(String pNoMatchTripId) {
        this.noMatchTripId = pNoMatchTripId;
    }

    public String getNoMatchTripId() {
        return(this.noMatchTripId);
    }

    public void setDivideToTripId(String pDivideToTripId) {
        this.divideToTripId = pDivideToTripId;
    }

    public String getDivideToTripId() {
        return(this.divideToTripId);
    }
    public void setDivideFromTripId(String pDivideFromTripId) {
        this.divideFromTripId = pDivideFromTripId;
    }

    public String getDivideFromTripId() {
        return(this.divideFromTripId);
    }

    public void setForeignTripParamList(List pNewValue) {
        this.foreignTripParamList = pNewValue;
    }

    public List getForeignTripParamList() {
        if (this.foreignTripParamList == null) {
            this.foreignTripParamList = new ArrayList();
        }
        return(this.foreignTripParamList);
    }

    public void setOriginalMessage(String pNewValue) {
        this.originalMessage = pNewValue;
    }

    public String getOriginalMessage() {
        return(this.originalMessage);
    }

    public void setAirlineOwner(String pNewValue) {
        this.airlineOwner = pNewValue;
    }

    public String getAirlineOwner() {
        return(this.airlineOwner);
    }

    public void setUniqueMessageIdentifier(String pNewValue) {
        this.uniqueMessageIdentifier = pNewValue;
    }

    public String getUniqueMessageIdentifier() {
        return(this.uniqueMessageIdentifier);
    }

    /**
     * Access method for the mqSessionIndex property.
     * @return int
     */
    public int getMQSessionIndex() {
        return this.mqSessionIndex;
    }

    /**
     * Sets the value of the mqSessionIndex property.
     * @param pMQSessionIndex
     */
    public void setMQSessionIndex(int pMQSessionIndex) {
        this.mqSessionIndex = pMQSessionIndex;
    }

    /**
     * @return boolean
     */
    public boolean getIsNewRecordLocator() {
        return this.isNewRecordLocator;
    }

    /**
     * @param pNewValue
     */
    public void setIsNewRecordLocator(boolean pNewValue) {
        this.isNewRecordLocator = pNewValue;
    }

    /**
     *
     * @return String Office Code
     */
    public String getOfficeCode(){
        return this.officeCode;
    }

    /**
     *
     * @param pNewValue Office code
     */
    public void setOfficeCode(String pNewValue) {
        this.officeCode = pNewValue;
    }

    /**
     *
     * @return String AgentId
     */
    public String getAgentId(){
        return this.agentId;
    }

    /**
     *
     * @param pNewValue AgentId
     */
    public void setAgentId(String pNewValue) {
        this.agentId = pNewValue;
    }

    /**
     * This gets the reactivatedFromTripId attribute
     * @return String ReactivatedFromTripId
     */
    public String getReactivatedFromTripId() {
        return this.reactivatedFromTripId;
    }

    /**
     * This sets the reactivatedFromTripId attribute
     * @param pReactivatedFromTripId ReactivatedFromTripId
     */
    public void setReactivatedFromTripId(String pReactivatedFromTripId) {
        this.reactivatedFromTripId = pReactivatedFromTripId;
    }

    public void setRemarklist(ArrayList pRemarkList) {
        this.remarkArrayList = pRemarkList;
    }

    public void setContactArrayList(ArrayList pContaceList) {
        this.contactArrayList = pContaceList;
    }

    public void setQueueRequestArrayList(ArrayList pQueueRequestList) {
       this.queueRequestArrayList = pQueueRequestList;
    }

    public void setMailingArrayList(ArrayList pMallingArrayList) {
        this.mailingArrayList = pMallingArrayList;
    }

    /**
     * Access method for tripEotTime
     * @return
     */
    public long getTripEotTime() {
        return this.tripEotTime;
    }
    /**
     * Sets the value of Trip Eot Time
     * @param pTripEotTime
     */
    public void setTripEotTime(long pTripEotTime) {
        this.tripEotTime = pTripEotTime;
    }

    /**
     * Holds the details of the responsibility office that is being shared with
     * the existing responsibility in case of host booking.
     */
    private String sharedResponsibilityDetails;

    /**
     * Holds the responsibility office that is being shared with the existing
     * responsibility in case of host booking.
     */
    private String sharedResponsibilityOffice;

    /**
     * Gets the details of the responsibility office that is being shared with
     * the existing responsibility in case of host booking.
     *
     * @return String - The current value of the
     * <code>sharedResponsibilityDetails</code> property.
     */
    public String getSharedResponsibilityDetails() {
        return this.sharedResponsibilityDetails;
    }

    /**
     * Gets the responsibility office that is being shared with the existing
     * responsibility in case of host booking.
     *
     * @return String - The current value of the
     * <code>sharedResponsibilityOffice</code> property.
     */
    public String getSharedResponsibilityOffice() {
        return this.sharedResponsibilityOffice;
    }

    /**
     * Sets the value of the details of the responsibility office that is being
     * shared with the existing responsibility in case of host booking.
     *
     * @param pSharedResponsibilityDetails The new value of the
     *  <code>sharedResponsibilityDetails</code> property.
     */
    public void setSharedResponsibilityDetails(String
                                               pSharedResponsibilityDetails) {
        this.sharedResponsibilityDetails = pSharedResponsibilityDetails;
    }

    /**
     * Sets the value of the responsibility office that is being shared with the
     * existing responsibility in case of host booking.
     *
     * @param pSharedResponsibilityOffice The new value of the
     * <code>sharedResponsibilityOffice</code> property.
     */
    public void setSharedResponsibilityOffice(String
                                              pSharedResponsibilityOffice) {
        this.sharedResponsibilityOffice = pSharedResponsibilityOffice;
    }

    public void setSsrArrayList(ArrayList pSsrArrayList) {
        this.ssrArrayList = pSsrArrayList;
    }
    
    
    
    /**
     * It is used to get the medif Array list
     */
    public ArrayList getMedifArrayList() {
    	
   	 if (this.medifArrayList == null) {
            this.medifArrayList = new ArrayList();
        }
		return medifArrayList;
	}

    /**
     * It is used to set the medif Array list
     */
	public void setMedifArrayList(ArrayList medifArrayList) {
		this.medifArrayList = medifArrayList;
	}
	
    /**
     * the type of the travel agency that update the booking
     */
    private int agencyType;


	
	public int getAgencyType() {
		return agencyType;
	}


	public void setAgencyType(int agencyType) {
		this.agencyType = agencyType;
	}

	/**
     * the agency of the travel agent that created the booking
     */
	private String agencyName;

	public String getAgencyName() {
		return agencyName;
	}


	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}


}
