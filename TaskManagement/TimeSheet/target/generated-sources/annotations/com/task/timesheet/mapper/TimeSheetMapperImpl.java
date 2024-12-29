package com.task.timesheet.mapper;

import com.task.enums.WorkType;
import com.task.modal.TimeSheet;
import com.task.timesheet.requestPayload.TimeSheetRequest;
import com.task.timesheet.responsePayload.TimeSheetResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-23T22:40:13+0530",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.13 (Ubuntu)"
)
@Component
public class TimeSheetMapperImpl extends TimeSheetMapper {

    @Override
    public TimeSheet timeSheetRequestMapper(TimeSheetRequest timeSheetRequest) {
        if ( timeSheetRequest == null ) {
            return null;
        }

        TimeSheet timeSheet = new TimeSheet();

        timeSheet.setEndDateTime( timeSheetRequest.getEndDateTime() );
        timeSheet.setHours( timeSheetRequest.getHours() );
        timeSheet.setStartDateTime( timeSheetRequest.getStartDateTime() );
        timeSheet.setTask( timeSheetRequest.getTask() );
        timeSheet.setTaskDescription( timeSheetRequest.getTaskDescription() );
        timeSheet.setTimeSheetUid( timeSheetRequest.getTimeSheetUid() );
        if ( timeSheetRequest.getWorkType() != null ) {
            timeSheet.setWorkType( Enum.valueOf( WorkType.class, timeSheetRequest.getWorkType() ) );
        }

        return timeSheet;
    }

    @Override
    public TimeSheet updateTimeSheetRequestMapper(TimeSheetRequest timeSheetRequest, TimeSheet timeSheet) {
        if ( timeSheetRequest == null ) {
            return timeSheet;
        }

        if ( timeSheetRequest.getEndDateTime() != null ) {
            timeSheet.setEndDateTime( timeSheetRequest.getEndDateTime() );
        }
        if ( timeSheetRequest.getHours() != null ) {
            timeSheet.setHours( timeSheetRequest.getHours() );
        }
        if ( timeSheetRequest.getStartDateTime() != null ) {
            timeSheet.setStartDateTime( timeSheetRequest.getStartDateTime() );
        }
        if ( timeSheetRequest.getTask() != null ) {
            timeSheet.setTask( timeSheetRequest.getTask() );
        }
        if ( timeSheetRequest.getTaskDescription() != null ) {
            timeSheet.setTaskDescription( timeSheetRequest.getTaskDescription() );
        }
        if ( timeSheetRequest.getTimeSheetUid() != null ) {
            timeSheet.setTimeSheetUid( timeSheetRequest.getTimeSheetUid() );
        }
        if ( timeSheetRequest.getWorkType() != null ) {
            timeSheet.setWorkType( Enum.valueOf( WorkType.class, timeSheetRequest.getWorkType() ) );
        }

        return timeSheet;
    }

    @Override
    public TimeSheet deleteTimeSheetRequestMapper(Integer deleteFlag, TimeSheet timeSheet) {
        if ( deleteFlag == null ) {
            return timeSheet;
        }

        if ( deleteFlag != null ) {
            timeSheet.setDeleteFlag( deleteFlag );
        }

        return timeSheet;
    }

    @Override
    public List<TimeSheetResponse> timeSheetModalAllMapper(List<TimeSheet> timeSheet) {
        if ( timeSheet == null ) {
            return null;
        }

        List<TimeSheetResponse> list = new ArrayList<TimeSheetResponse>( timeSheet.size() );
        for ( TimeSheet timeSheet1 : timeSheet ) {
            list.add( timeSheetModalMapper( timeSheet1 ) );
        }

        return list;
    }

    @Override
    public TimeSheetResponse timeSheetModalMapper(TimeSheet timeSheet) {
        if ( timeSheet == null ) {
            return null;
        }

        TimeSheetResponse timeSheetResponse = new TimeSheetResponse();

        timeSheetResponse.setEndDateTime( timeSheet.getEndDateTime() );
        timeSheetResponse.setHours( timeSheet.getHours() );
        timeSheetResponse.setStartDateTime( timeSheet.getStartDateTime() );
        timeSheetResponse.setTask( timeSheet.getTask() );
        timeSheetResponse.setTaskDescription( timeSheet.getTaskDescription() );
        timeSheetResponse.setTimeSheetUid( timeSheet.getTimeSheetUid() );
        timeSheetResponse.setUserId( timeSheet.getUserId() );
        timeSheetResponse.setWorkType( timeSheet.getWorkType() );

        return timeSheetResponse;
    }

    @Override
    public Set<TimeSheetResponse> timeSheetModalAllMapper(Page<TimeSheet> timeSheet) {
        if ( timeSheet == null ) {
            return null;
        }

        Set<TimeSheetResponse> set = new LinkedHashSet<TimeSheetResponse>();
        for ( TimeSheet timeSheet1 : timeSheet ) {
            set.add( timeSheetModalMapper( timeSheet1 ) );
        }

        return set;
    }
}
