package com.fusm.servicebroker.servicebroker.service.ms_safety_mesh;

import com.fusm.servicebroker.servicebroker.model.ms_safety_mesh.ColumnRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IColumnService {

    Object getColumns();
    String createColumn(ColumnRequest columnRequest);
    String updateColumn(ColumnRequest columnRequest, Integer columnId);

}
