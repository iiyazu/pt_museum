package com.iiyatsu.mapper;

import com.iiyatsu.pojo.ArtifaQueryParam;
import com.iiyatsu.pojo.artifact.Artifact;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArtifactMapper {

    /**
     * 根据id查询artifact
     * @param id
     * @return
     */
    @Select("Select * from artifact where id = #{id}")
    Artifact selectById(Integer id);


    /**
     * 根据参数查询artifact
     * @param param
     * @return
     */
    List<Artifact> selectByParam(ArtifaQueryParam param);

    /**
     * 批量删除artifact
     * @param ids
     */
    void deleteBatch(List<Integer> ids);

    /**
     * 插入artifact
     * @param artifact
     */
    @Insert("insert into artifact(id, name, image, description, age) " +
            "values(#{id}, #{name}, #{image}, #{description}, #{age})")
    void insert(Artifact artifact);

    /**
     * 根据id更新artifact
     * @param artifact
     */
    void updateById(Artifact artifact);
}
