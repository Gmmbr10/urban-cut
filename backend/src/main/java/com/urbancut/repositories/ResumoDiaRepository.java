package com.urbancut.repositories;

import com.urbancut.core.Repository;
import com.urbancut.models.ResumoDia;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ResumoDiaRepository extends Repository {

    public List<ResumoDia> summary(LocalDate data, int idBarbeiro) throws SQLException {
        List<ResumoDia> summary = new ArrayList<>();

        String query = "SELECT * FROM atendimentos WHERE id_barbeiro = ? AND atendimento BETWEEN ? AND ?";
        PreparedStatement stm = this.database.prepareStatement(query);

        stm.setInt(1,idBarbeiro);
        stm.setTimestamp(2, Timestamp.valueOf(LocalDateTime.of(data, LocalTime.MIN)));
        stm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.of(data, LocalTime.MAX)));

        ResultSet response = stm.executeQuery();

        while(response.next()){
            ResumoDia resumoDia = new ResumoDia.ResumoDiaBuilder()
                    .data(response.getTimestamp("atendimento").toLocalDateTime().toLocalDate())
                    .horario(response.getTimestamp("atendimento").toLocalDateTime().toLocalTime())
                    .cliente((new ClienteRepository()).searchById(response.getInt("id_cliente")))
                    .build();

            if ( resumoDia.getCliente().getIdCliente() != null ){
                summary.add(resumoDia);
            }
        }

        return summary;
    }
}
