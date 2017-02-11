package com.echo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.echo.entity.Appointment;

public interface AppointmentDao {

    /**
     * 插入预约图书记录
     * 
     * @param bookId
     * @param studentId
     * @return 插入的行数
     */
    int insertAppointment(@Param("bookId") long bookId, @Param("studentId") long studentId);

    /**
     * 通过主键查询预约图书记录，并且携带图书实体
     * 
     * @param bookId
     * @param studentId
     * @return
     */
    Appointment queryByKeyWithBook(@Param("bookId") long bookId, @Param("studentId") long studentId);
    
    /**
     * 获取对应图书编号的借阅记录
     */
    List<Appointment> queryAppointmentsByBookId(@Param("bookId") long bookId);

}