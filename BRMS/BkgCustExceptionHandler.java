/* ====================================== */
/* Copyright (c) 2007 Unisys Corporation. */
/*          All rights reserved.          */
/*          UNISYS CONFIDENTIAL           */
/* ====================================== */

package com.unisys.trans.aircore.bkg.customerprofile.utils;

import com.unisys.trans.aircore.bkg.constant.BKGConstants;
import com.unisys.trans.aircore.bkg.constant.BKGReasonCodeConstants;
import com.unisys.trans.aircore.bkg.constant.BkgLogIdConstants;
import com.unisys.trans.aircore.bkg.utils.ExceptionCreator;
import com.unisys.trans.aircore.customers.constants.CustomerReasonCodeConstants;
import com.unisys.trans.shared.util.constant.SharedConstants;
import com.unisys.trans.shared.util.response.SharedException;
import com.unisys.trans.shared.validator.constant.SharedValidationReasonCodeConstants;

/**
 * This class contains the methods that handles exceptions.
 * These exceptions are the ones thrown by customers when booking tries to
 * create a profile from Booking.
 * @author Unisys
 * @version 1.0
 * @since 2.8
 */

public class BkgCustExceptionHandler {
    /**
     * <p>
     * This is a static method where in the Customer's Address Error messages
     * will be converted into Booking specific error messages.
     * @param pCustomerReasonCode The value of the <code>aCustomerReasonCode</code>.
     * @throws SharedException A SharedException is thrown.
     */
    public static final void handleAddressException(final int pCustomerReasonCode) throws SharedException {
        if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_ADDRESS_LINE_1) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_ADDRESS_LINE_1,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11728, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Address Line 1 is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_ADDRESS_LINE_2) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_ADDRESS_LINE_2,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11729, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Address Line 2 is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_ADDRESS_LINE_3) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_ADDRESS_LINE_3,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11730, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Address Line 3 is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_AREA_CODE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_AREA_CODE, SharedConstants.FATAL_WRITE_ERROR,
                    BkgLogIdConstants.LOG_ID_11731, BKGConstants.LOG_INFO, "",
                    "CustomerGateway.ceateCustomerProfile() :" + "The Area Code is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_CITY_CODE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_CITY_CODE, SharedConstants.FATAL_WRITE_ERROR,
                    BkgLogIdConstants.LOG_ID_11732, BKGConstants.LOG_INFO, "",
                    "CustomerGateway.ceateCustomerProfile() :" + "The City Code is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_COUNTRY_CODE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_COUNTRY_CODE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11733, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Country Code is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.ADDRESS_TYPE_UNIQUE_PER_PROFILE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_ADDRESS_TYPE_UNIQUE_PER_PROFILE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12068, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Address Type is unique per profile.");
        }
        else if (pCustomerReasonCode ==
            CustomerReasonCodeConstants.COMPANY_AVAILABLE_IF_ADDRESS_TYPE_IS_BUSINESS) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.COMPANY_AVAILABLE_IF_ADDRESS_TYPE_IS_BUSINESS,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12069, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Company Name is mandatory for Business Address.");
        }
    }

    /**
     * <p>
     * This is a static method where in the Customer's Contact Related Error
     * messages will be converted into Booking specific error messages.
     * @throws SharedException A SharedException is thrown.
     * @param pCustomerReasonCode The customer reason code.
     */
    public static final void handleContactExceptions(final int pCustomerReasonCode) throws SharedException {
        if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_EMAIL_ADDRESS) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_EMAIL_ADDRESS,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11725, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Email Address is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_EMAIL_ADDRESS_TYPE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_EMAIL_ADDRESS_TYPE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11726, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Email Address Type is invalid.");
        }
        else if (pCustomerReasonCode == SharedValidationReasonCodeConstants.INVALID_LOCAL_NUMBER) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_LOCAL_NUMBER,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11727, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Local Number is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_PHONE_TYPE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_PHONE_TYPE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12056, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Phone Type is invalid.");

        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.CITY_CODE_IS_MANDATORY) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_CITY_CODE_IS_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12055, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The City Code is mandatory.");

        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12054, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Contact is Mandatory.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.PHONE_TYPE_UNIQUE_PER_PROFILE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PHONE_TYPE_UNIQUE_PER_PROFILE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12063, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Phone Type is unique per profile.");
        }

        else if (pCustomerReasonCode == CustomerReasonCodeConstants.PHONE_TYPE_ALREADY_EXISTING) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PHONE_TYPE_ALREADY_EXISTING,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12064, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Phone Type already exit..");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.EMAILADDRESS_TYPE_ALREADY_EXISTING) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_EMAILADDRESS_TYPE_ALREADY_EXISTING,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12065, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Eamil Address type already exist.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.EMAILADDRESS_TYPE_UNIQUE_PER_PROFILE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_EMAILADDRESS_TYPE_UNIQUE_PER_PROFILE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12066, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Email Address Type unique per profile.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.PHONE_OR_EMAIL_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PHONE_OR_EMAIL_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12248, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Phone/ Email not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.PHONE_OR_ADDRESS_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PHONE_OR_ADDRESS_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12249, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Phone/ Address not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.EMAIL_OR_ADDRESS_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_EMAIL_OR_ADDRESS_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12250, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Email/ Address not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.PHONE_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PHONE_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12251, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Phone not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.EMAIL_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_EMAIL_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12252, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Email not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.ADDRESS_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_ADDRESS_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12253, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Address not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.PHONE_AND_EMAIL_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PHONE_AND_EMAIL_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12254, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Phone and Email not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.PHONE_AND_ADDRESS_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PHONE_AND_ADDRESS_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12247, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Phone and Address not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.EMAIL_AND_ADDRESS_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_EMAIL_AND_ADDRESS_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12255, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Email and Address not found.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.PHONE_AND_EMAIL_AND_ADDRESS_CONTACT_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PHONE_AND_EMAIL_AND_ADDRESS_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12256, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Phone, Email and Address not found");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.LOCAL_NUMBER_IS_MANDATORY) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_LOCAL_NUMBER_IS_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12257, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" +
                    "Local Number is Mandatory");
        }
    }

    /**
     * <p>
     * This is a static method where in the Customer's Basi Inormation Error messages
     * will be converted into Booking specific error messages.
     * @throws SharedException A SharedException is thrown.
     * @param pCustomerReasonCode The customer reason code.
     */
    public static final void handleCustBasicInfoExceptions(final int pCustomerReasonCode)
            throws SharedException {
        if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_BUSINESS_TITLE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_BUSINESS_TITLE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11718, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Business Titel is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.TITLE_INVALID) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_TITLE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140549, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Customer Title is invalid.");
        } else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_SALUTATION) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_SALUTATION,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11719, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Salutation is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_PREFERRED_LANGUAGE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_PREFERRED_LANGUAGE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11720, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Preferred language is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_SURNAME) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.CUST_PROFILE_INVALID_SURNAME,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11721, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Surname is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_NATIONALITY) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_NATIONALITY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11722, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Nationality is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_GENDER) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.CUST_PROFILE_INVALID_GENDER,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11723, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Gender is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_BIRTH_DATE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_BIRTH_DATE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11724, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Birth Date is invalid.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_FIRST_NAME) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_FIRST_NAME,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12052, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The First Name is invalid.");
        }
    }

    /**
     * <p>
     * This is a static method where in the Customer's General Error
     * messages will be converted into Booking specific error messages.
     * @param pCustomerReasonCode A CustomerReasonCode.
     * @throws SharedException A SharedException is thrown.
     */
    public static final void handleGeneralExceptions(final int pCustomerReasonCode) throws SharedException {
        if (pCustomerReasonCode == CustomerReasonCodeConstants.UNEXPECTED_EXCEPTION) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.UNEXPECTED_EXCEPTION,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11734, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "An unexpected error occured.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.REMOTE_EXCEPTION) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.REMOTE_EXCEPTION,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11735, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :"
                            + "A General Remote Exception has occured.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.EJB_CREATE_EXCEPTION) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.EJB_CREATE_EXCEPTION,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11736, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "An EJB Create Exception has occured.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.DUPLICATE_PROFILE_EXISTS) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.DUPLICATE_PROFILE_EXISTS,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11737, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Duplicate Profile exist.");
        }
        else if (pCustomerReasonCode == CustomerReasonCodeConstants.CONTACT_NOT_FOUND) {

        	throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.CUST_PROFILE_CONTACT_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_11737, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Duplicate Profile exist.");
        }
        
        
        else if (CustomerReasonCodeConstants. AFFILIATION_TYPE_MANDATORY == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.AFFILIATION_TYPE_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140546,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Affiliation type mandatory.");
        }
        
       
    }

    /**
     * <p>
     * This is a static method where in the Customer's Preference Related Error
     * messages will be converted into Booking specific error messages.
     * @param pCustomerReasonCode A CustomerReasonCode.
     * @throws SharedException A SharedException is thrown.
     */
    public final static void handlePreferenceException (final int pCustomerReasonCode)
        throws SharedException {
        if (pCustomerReasonCode == CustomerReasonCodeConstants.PARTNER_CODE_NOT_FOUND) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_PARTNER_CODE_NOT_FOUND,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12051, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Partner Code not found.");
        }
        if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_PREFERENCE_TYPE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_PREFERENCE_TYPE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12051, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "The Preference Type is invalid.");
        }
        if (pCustomerReasonCode == CustomerReasonCodeConstants.VENDOR_CODE_OR_TYPE_MANDATORY) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_VENDOR_CODE_OR_TYPE_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12241, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Hotel Vendor/Hotel Vendor Name, Room " +
                            "Type or Text is a mandatory field.");
        }
        if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_CLASS_CODE) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_CLASS_CODE,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12243, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Class code is invalid.");
        }
        if (pCustomerReasonCode == CustomerReasonCodeConstants.INVALID_VENDOR_TEXT) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.CUST_PROFILE_INVALID_VENDOR_TEXT,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_12244, BKGConstants.LOG_INFO,
                    "", "CustomerGateway.ceateCustomerProfile() :" + "Vendor Name is invalid.");
        }
    }
    
    
    
    /**
     * <p>
     * This is a static method where in the Customer's Profile Number Related Error
     * messages will be converted into Booking specific error messages.
     * @param pCustomerReasonCode The customer reason code.
     * @throws SharedException A SharedException is thrown.
     */
    
    public static final void handleCustNumberExceptions(final int pCustomerReasonCode) throws SharedException {
        if (CustomerReasonCodeConstants.PRIMARY_IDENTIFIER_EXISTS == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.CUST_PROFILENUMBER_EXISTS,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140537,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Customer Profile Number exists.");
        }
        if (CustomerReasonCodeConstants.FF_CARD_ALREADY_EXISTS == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.CUST_PROFILENUMBER_EXISTS,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140537,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Customer identifier Number exists."); 
        }
    }

    /**
     * <p>
     * This is a static method where in the Customer's Travel Document Related
     * Error messages will be converted into Booking specific error messages.
     * 
     * @param pCustomerReasonCode The customer reason code.
     * @throws SharedException A SharedException is thrown.
     */

    public static final void handleTravelDocumentExceptions(final int pCustomerReasonCode)
            throws SharedException {
        if (CustomerReasonCodeConstants.DOCUMENT_TYPE_MANDATORY == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.DOCUMENT_TYPE_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140538,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Document Type Mandatory.");
        }

        else if (CustomerReasonCodeConstants.DOCUMENT_NUMBER_MANDATORY == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.DOCUMENT_NUMBER_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140539,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Document Number  Mandatory.");
        }

        else if (CustomerReasonCodeConstants.ISSUING_COUNTRY_MANDATORY == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.ISSUE_COUNTRY_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140540,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Issuing Country is Mandatory.");
        }

        else if (CustomerReasonCodeConstants.ISSUE_DATE_MANDATORY == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.ISSUE_DATE_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140541,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Issuing Date  Mandatory.");
        }

        else if (CustomerReasonCodeConstants.EXPIRY_DATE_MANDATORY == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.EXPIRY_DATE_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140542,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Expiry Date  Mandatory.");
        }

        else if (CustomerReasonCodeConstants.NATIONALITY_CODE_DOES_NOT_EXIST == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(
                    BKGReasonCodeConstants.NATIONALITY_CODE_DOES_NOT_EXIST,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140543,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "The Nationality code does not exist .");
        }

        else if (CustomerReasonCodeConstants.TRAVEL_DOCUMENT_EXISTS == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.TRAVEL_DOCUMENT_EXISTS,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140544,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Travel Document Exists.");
        }

        else if (CustomerReasonCodeConstants.FIRST_NAME_MANDATORY == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.FIRST_NAME_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140545,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Invalid First Name.");
        }

        else if (CustomerReasonCodeConstants.NATIONALITY_MANDATORY == pCustomerReasonCode) {
            throw ExceptionCreator.createSharedException(BKGReasonCodeConstants.NATIONALITY_MANDATORY,
                    SharedConstants.FATAL_WRITE_ERROR, BkgLogIdConstants.LOG_ID_140547,
                    BKGConstants.LOG_INFO, "", "CustomerGateway.ceateCustomerProfile() :"
                            + "Nationality is Mandatory .");
        }

    } 
    
   
}
