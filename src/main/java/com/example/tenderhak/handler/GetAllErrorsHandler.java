package com.example.tenderhak.handler;

import com.example.tenderhak.model.AllErrorsModel;
import com.example.tenderhak.model.ClassEnum;
import com.example.tenderhak.model.ErrorModel;
import com.example.tenderhak.model.ResponseErrorsData;
import com.example.tenderhak.repository.*;
import com.example.tenderhak.repository.Error;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllErrorsHandler {

    private final ErrorRepository errorRepository;
    private final TypeRepository typeRepository;
    private final DescriptionRepository descriptionRepository;

    public ResponseEntity<List<ResponseErrorsData>> handle() {

        List<ResponseErrorsData> response = new ArrayList<>();
        for(int i = 0; i < ClassEnum.values().length; i++) {
            ResponseErrorsData responseErrorsData = new ResponseErrorsData();
            responseErrorsData.setClassEnum(ClassEnum.values()[i]);
            responseErrorsData.setClassDescription(descriptionRepository.findByTitle(ClassEnum.values()[i].toString()).getDescription());
            List<ErrorModel> errorModelList = new ArrayList<>();
            List<Type> typeList = typeRepository.findAllByClassType(ClassEnum.values()[i]);
            for(Type type : typeList) {
                ErrorModel model = new ErrorModel();
                model.setId(type.getId());
                model.setKey(type.getKey());
                model.setType(type.getType());
                model.setTitle(type.getTitle());
                model.setDescription(type.getDescription());
                model.setSolution(type.getSolution());
                model.setStatus(type.getStatus());
                model.setTypeDescription(descriptionRepository.findByTitle(model.getType().toString()).getDescription());
                Integer count = errorRepository.findCountFromTypeId(type.getId());
                model.setCount(count);
                model.setLastDate(errorRepository.findLastErrorDate(type.getId()));
                errorModelList.add(model);
            }
            responseErrorsData.setErrorModelList(errorModelList);
            response.add(responseErrorsData);
        }

//        List<AllErrorsModel> allErrorsModelList = new ArrayList<>();
//
//        List<Type> typeList = typeRepository.findAll();
//        for(Type type : typeList) {
//            AllErrorsModel model = new AllErrorsModel();
//            model.setClassType(type.getClassType());
//            model.setType(type.getType());
//            model.setKey(type.getKey());
//            Integer count = errorRepository.findCountFromTypeId(type.getId());
//            model.setCount(count);
//            allErrorsModelList.add(model);
//        }

        return new ResponseEntity<List<ResponseErrorsData>>(response, HttpStatus.OK);
    }

}
