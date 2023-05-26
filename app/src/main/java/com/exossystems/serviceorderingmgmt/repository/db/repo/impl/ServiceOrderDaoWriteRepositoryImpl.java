package com.exossystems.serviceorderingmgmt.repository.db.repo.impl;

import com.exossystems.serviceorderingmgmt.api.model.domain.entity.ServiceOrder;
import com.exossystems.serviceorderingmgmt.api.model.domain.request.ServiceOrderRequest;
import com.exossystems.serviceorderingmgmt.repository.db.model.ServiceOrderDaoWriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
@Repository
@RequiredArgsConstructor
public class ServiceOrderDaoWriteRepositoryImpl extends JdbcDaoSupport implements ServiceOrderDaoWriteRepository {
    private final DataSource dataSource;

    @PostConstruct
    public void initialize() {
        setDataSource(dataSource);
    }

    private static final String INSERT_SERVICE_ORDER =
            """
                    INSERT INTO public.service_order(id, href, category, completion_date, description, expected_completion_date, external_id, notification_contact, order_date, priority, requested_completion_date, start_date, state, base_type, schema_location, type) 
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;


    @Override
    public boolean saveServiceOrder(ServiceOrder serviceOrder) {
        return Objects.requireNonNull(getJdbcTemplate().execute(INSERT_SERVICE_ORDER, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                preparedStatement.setString(1, serviceOrder.getId());
                preparedStatement.setString(2, serviceOrder.getHref());
                preparedStatement.setString(3, serviceOrder.getCategory());
                preparedStatement.setTimestamp(4, serviceOrder.getCompletionDate());
                preparedStatement.setString(5, serviceOrder.getDescription());
                preparedStatement.setTimestamp(6, serviceOrder.getExpectedCompletionDate());
                preparedStatement.setString(7, serviceOrder.getExternalId());
                preparedStatement.setString(8, serviceOrder.getNotificationContact());
                preparedStatement.setTimestamp(9, serviceOrder.getOrderDate());
                preparedStatement.setString(10, serviceOrder.getPriority());
                preparedStatement.setTimestamp(11, serviceOrder.getRequestedCompletionDate());
                preparedStatement.setTimestamp(12, serviceOrder.getStartDate());
                preparedStatement.setString(13, serviceOrder.getState());
                preparedStatement.setString(14, serviceOrder.getBaseType());
                preparedStatement.setString(15, serviceOrder.getSchemaLocation());
                preparedStatement.setString(16, serviceOrder.getType());

                return preparedStatement.execute();
            }
        }
        ));
    }

    @Override
    public Long getNextSequenceValue(String sequenceName) {
        String sql = "SELECT nextval('%s')";
        final SqlRowSet sqlRowSet = Objects.requireNonNull(getJdbcTemplate()).queryForRowSet(String.format(sql, sequenceName));
        sqlRowSet.next();
        return sqlRowSet.getLong(1);
    }


}

