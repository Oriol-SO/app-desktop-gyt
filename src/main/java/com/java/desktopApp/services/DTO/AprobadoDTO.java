package com.java.desktopApp.services.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AprobadoDTO {

    private Long id;
    private Long file_id;
    private Timestamp created_at;
}
