package com.clockData.mapper;

import com.clockData.responsePayload.TimeSheetResponse;
import com.task.modal.TimeSheet;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-01T22:07:21+0530",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.36.0.v20231114-0937, environment: Java 17.0.13 (Ubuntu)"
)
@Component
public class ClockDataMapperImpl extends ClockDataMapper {

    @Override
    public Set<TimeSheetResponse> timeSheetModalAllMapper(Page<TimeSheet> timeSheet) {
        if ( timeSheet == null ) {
            return null;
        }

        Set<TimeSheetResponse> set = new LinkedHashSet<TimeSheetResponse>();
        for ( TimeSheet timeSheet1 : timeSheet ) {
            set.add( timeSheetToTimeSheetResponse( timeSheet1 ) );
        }

        return set;
    }

    protected TimeSheetResponse timeSheetToTimeSheetResponse(TimeSheet timeSheet) {
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
}
