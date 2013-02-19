package com.adslur.szt.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;

import com.jgoodies.validation.util.DefaultValidationResultModel;

import com.adslur.szt.domen.DataInputDemand;
import com.adslur.szt.validation.DataInputDemandValidator;


public class DataInputDemandModel extends PresentationModel<DataInputDemand> {

    private final ValidationResultModel validationResultModel;




    public DataInputDemandModel(DataInputDemand d) {
        super(d);
        validationResultModel = new DefaultValidationResultModel();
        initEventHandling();
        updateValidationResult();
    }


   

    public ValidationResultModel getValidationResultModel() {
        return validationResultModel;
    }


  
    private void initEventHandling() {
        PropertyChangeListener handler = new ValidationUpdateHandler();
        addBeanPropertyChangeListener(handler);
        getBeanChannel().addValueChangeListener(handler);
    }




    private void updateValidationResult() {
        DataInputDemand  d = getBean();
        ValidationResult result = new DataInputDemandValidator().validate(d);
        validationResultModel.setResult(result);
    }


    private final class ValidationUpdateHandler implements PropertyChangeListener {

        public void propertyChange(PropertyChangeEvent evt) {
            updateValidationResult();
        }

    }

}
