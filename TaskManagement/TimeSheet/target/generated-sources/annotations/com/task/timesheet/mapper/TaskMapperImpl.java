package com.task.timesheet.mapper;

import com.task.modal.StandardTask;
import com.task.timesheet.requestPayload.TaskRequest;
import com.task.timesheet.responsePayload.StandardTaskResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-01T22:07:22+0530",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.13 (Ubuntu)"
)
@Component
public class TaskMapperImpl extends TaskMapper {

    @Override
    public StandardTask standardTaskRequestMapper(TaskRequest taskRequest) {
        if ( taskRequest == null ) {
            return null;
        }

        StandardTask standardTask = new StandardTask();

        standardTask.setAssignTo( taskRequest.getAssignTo() );
        standardTask.setClient( taskRequest.getClient() );
        standardTask.setEndDateTime( taskRequest.getEndDateTime() );
        standardTask.setHours( taskRequest.getHours() );
        standardTask.setProduct( taskRequest.getProduct() );
        standardTask.setStartDateTime( taskRequest.getStartDateTime() );
        standardTask.setTaskTitle( taskRequest.getTaskTitle() );

        return standardTask;
    }

    @Override
    public StandardTask updateStandardTasktRequestMapper(TaskRequest taskRequest, StandardTask standardTask) {
        if ( taskRequest == null ) {
            return standardTask;
        }

        if ( taskRequest.getAssignTo() != null ) {
            standardTask.setAssignTo( taskRequest.getAssignTo() );
        }
        if ( taskRequest.getClient() != null ) {
            standardTask.setClient( taskRequest.getClient() );
        }
        if ( taskRequest.getEndDateTime() != null ) {
            standardTask.setEndDateTime( taskRequest.getEndDateTime() );
        }
        if ( taskRequest.getHours() != null ) {
            standardTask.setHours( taskRequest.getHours() );
        }
        if ( taskRequest.getProduct() != null ) {
            standardTask.setProduct( taskRequest.getProduct() );
        }
        if ( taskRequest.getStartDateTime() != null ) {
            standardTask.setStartDateTime( taskRequest.getStartDateTime() );
        }
        if ( taskRequest.getTaskTitle() != null ) {
            standardTask.setTaskTitle( taskRequest.getTaskTitle() );
        }

        return standardTask;
    }

    @Override
    public StandardTask deleteStandardTaskRequestMapper(Integer deleteFlag, StandardTask standardTask) {
        if ( deleteFlag == null ) {
            return standardTask;
        }

        if ( deleteFlag != null ) {
            standardTask.setDeleteFlag( deleteFlag );
        }

        return standardTask;
    }

    @Override
    public List<StandardTaskResponse> standardTaskModalAllMapper(List<StandardTask> standardTask) {
        if ( standardTask == null ) {
            return null;
        }

        List<StandardTaskResponse> list = new ArrayList<StandardTaskResponse>( standardTask.size() );
        for ( StandardTask standardTask1 : standardTask ) {
            list.add( standardTaskModalMapper( standardTask1 ) );
        }

        return list;
    }

    @Override
    public StandardTaskResponse standardTaskModalMapper(StandardTask standardTask) {
        if ( standardTask == null ) {
            return null;
        }

        StandardTaskResponse standardTaskResponse = new StandardTaskResponse();

        standardTaskResponse.setAssignTo( standardTask.getAssignTo() );
        standardTaskResponse.setClient( standardTask.getClient() );
        standardTaskResponse.setEndDateTime( standardTask.getEndDateTime() );
        standardTaskResponse.setHours( standardTask.getHours() );
        standardTaskResponse.setProduct( standardTask.getProduct() );
        standardTaskResponse.setStartDateTime( standardTask.getStartDateTime() );
        standardTaskResponse.setTasKUid( standardTask.getTasKUid() );
        standardTaskResponse.setTaskTitle( standardTask.getTaskTitle() );
        standardTaskResponse.setUserId( standardTask.getUserId() );

        return standardTaskResponse;
    }
}
