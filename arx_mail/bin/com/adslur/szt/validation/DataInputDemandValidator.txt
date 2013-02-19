package com.adslur.szt.validation;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;

import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

import com.adslur.szt.domen.DataInputDemand;


public final class DataInputDemandValidator implements Validator<DataInputDemand> {


    public ValidationResult validate(DataInputDemand   demand) {
                  
        PropertyValidationSupport support =
            new PropertyValidationSupport(demand, "TLF");






           if ( ValidationUtils.isBlank(demand.getTlf() ) ) {
                  support.addWarning("TLF No", "is mandatory");
           }
            else if  ( !ValidationUtils.isNumeric(demand.getTlf()) ){
             
                             support.addError("TLF No", "is mandatory");
                            //  System.out.println("----------TRACE   ===> VALIDATE DEMAND  SUPPORT:" + support.getResult() );
                     }
            
           //////////////////

          
            if ( ValidationUtils.isBlank(demand.getNtdop() ) ) {
                  support.addWarning("TLFDOP No", "is mandatory");
           }
            else if  ( !ValidationUtils.isNumeric(demand.getNtdop()) ){
             
                             support.addError("TLFDOP No", "is mandatory");
                             // System.out.println("----------TRACE   ===> VALIDATE DEMAND  SUPPORT:" + support.getResult() );
                     }

            if ( ValidationUtils.isBlank(demand.getEmail() ) ) {
                  support.addWarning("EMAIL No", "is mandatory");
           }

           if ( ValidationUtils.isBlank(demand.getFax() ) ) {
                  support.addWarning("FAX No", "is mandatory");
           }
            
            if ( ValidationUtils.isBlank(demand.getFamdop() ) ) {
                  support.addWarning("FAMDOP No", "is mandatory");
           }
 
            if ( ValidationUtils.isBlank(demand.getImdop() ) ) {
                  support.addWarning("IMDOP No", "is mandatory");
           }

            if ( ValidationUtils.isBlank(demand.getOtdop() ) ) {
                  support.addWarning("OTDOP No", "is mandatory");
           }





        return support.getResult();
    }


}
