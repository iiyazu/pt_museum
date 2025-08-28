package com.iiyatsu.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iiyatsu.mapper.ArtifactMapper;
import com.iiyatsu.pojo.ArtifaQueryParam;
import com.iiyatsu.pojo.PageResult;
import com.iiyatsu.pojo.artifact.Artifact;
import com.iiyatsu.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtifactServiceImpl implements ArtifactService {

    @Autowired
    private ArtifactMapper artifactMapper;


    /**
     * 根据 ID 查询 Artifact 的详细信息
     * @param id Artifact 的 ID
     * @return Artifact 详细信息
     */
    @Override
    public Artifact getInfo(Integer id) {
        return artifactMapper.selectById(id);
    }

    /**
     * 分页查询 Artifact 列表
     * @param param 查询参数（包含分页信息和其他条件）
     * @return PageResult<Artifact> 封装了分页数据和元信息的结果
     */
    @Override
    public PageResult<Artifact> page(ArtifaQueryParam param) {
        // 1. 设置分页参数
        // PageHelper 会自动拦截接下来的第一个 MyBatis 查询
        // 注意：PageHelper 的页码通常从 1 开始
        PageHelper.startPage(param.getPage(), param.getPageSize());

        // 2. 执行查询
        // 调用 Mapper 的方法查询符合条件的数据列表
        List<Artifact> artifactList = artifactMapper.selectByParam(param);

        // 3. 将查询结果包装成 PageInfo 对象
        // PageInfo 会自动包含总记录数、分页信息等
        PageInfo<Artifact> pageInfo = new PageInfo<>(artifactList);

        // 4. 创建 PageResult 对象并填充数据
        PageResult<Artifact> pageResult = new PageResult<>();
        pageResult.setTotal(pageInfo.getTotal()); // 设置总记录数
        pageResult.setRows(pageInfo.getList());   // 设置当前页的数据列表 (使用 'rows')

        return pageResult;
    }

    @Override
    public void delete(List<Integer> ids) {
        artifactMapper.deleteBatch(ids);
    }

    /**
     * 添加 Artifact
     * @param artifact Artifact 对象
     */
    @Override
    public void add(Artifact artifact) {
        artifactMapper.insert(artifact);
    }

    /**
     * 更新 Artifact
     * @param artifact Artifact 对象
     */
    @Override
    public void update(Artifact artifact) {
        artifactMapper.updateById(artifact);
    }
}
