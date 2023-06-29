<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperQuote}.${mapperName}">
    <sql id="sqlColumn">
        ${sqlColumn}
    </sql>

    <select id="selectByConditions" resultType="${entityQuote}.${entityName}">
        select
            <include refid="sqlColumn"/>
        from
            ${tableName}
        <where>
            <if test="${paramName} != null">
${selectDynamicSql}
            </if>
        </where>
        order by create_time desc
        ${pageSql}
    </select>

    <insert id="insert" useGeneratedKeys="true" keyProperty="id">
        insert into
            ${tableName}
        (
        <trim suffixOverrides=",">
${firstHalfSql}
        </trim>
        )
        values
        (
        <trim suffixOverrides=",">
${lowerHalfSql}
        </trim>
        )
    </insert>

    <update id="update" parameterType="${entityQuote}.${entityName}">
        update
            ${tableName}
        <set>
${updateSql}
        </set>
        where
            ${idSql}
    </update>

    <delete id="delete">
        delete from
            ${tableName}
        where
            ${idSql}
    </delete>

</mapper>