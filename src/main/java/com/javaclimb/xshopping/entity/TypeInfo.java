package com.javaclimb.xshopping.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Database Table Remarks:
 *   商品类别表
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table type_info
 */

@Table(name = "type_info")
public class TypeInfo {
    /**
     * Database Column Remarks:
     *   主键
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column type_info.id
     *
     * @mbg.generated
     */


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Database Column Remarks:
     *   类别名称
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column type_info.name
     *
     * @mbg.generated
     */
    private String name;

    /**
     * Database Column Remarks:
     *   类别描述
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column type_info.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column type_info.id
     *
     * @return the value of type_info.id
     *
     * @mbg.generated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column type_info.id
     *
     * @param id the value for type_info.id
     *
     * @mbg.generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column type_info.name
     *
     * @return the value of type_info.name
     *
     * @mbg.generated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column type_info.name
     *
     * @param name the value for type_info.name
     *
     * @mbg.generated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column type_info.description
     *
     * @return the value of type_info.description
     *
     * @mbg.generated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column type_info.description
     *
     * @param description the value for type_info.description
     *
     * @mbg.generated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}