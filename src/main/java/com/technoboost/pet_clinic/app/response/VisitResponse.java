package com.technoboost.pet_clinic.app.response;


import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class VisitResponse {

    private List<VisitDto> visitedList;
}
