package com.revature.dao;

import com.revature.model.Account;
import com.revature.utility.ConnectionUtility;

import java.sql.*;

public class AccountDao {

//    create a new account for an existing client X(id)
//    POST /clients/{client_id}/accounts

    public Account addAccount(int clientId, Account account) throws SQLException {
        try(Connection con = ConnectionUtility.getConnection()){
            String query = "INSERT INTO accounts (account_type, balance, customer_id) " +
                    "VALUES (?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, account.getAccountType());
            pstmt.setDouble(2, account.getBalance());
            pstmt.setInt(3,clientId);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();

            if(rs.next()){
                int accountNo = rs.getInt("account_no");
                String accountType = rs.getString("account_type");
                Double balance = rs.getDouble("balance");
                int customerId = rs.getInt("customer_id");

                return new Account(accountNo, accountType, balance, customerId);
            }
        }
        return null;
    }
}
