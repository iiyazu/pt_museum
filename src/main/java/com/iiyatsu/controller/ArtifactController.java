package com.iiyatsu.controller;

import com.iiyatsu.pojo.ArtifaQueryParam;
import com.iiyatsu.pojo.PageResult;
import com.iiyatsu.pojo.Result;
import com.iiyatsu.pojo.artifact.Artifact;
import com.iiyatsu.service.ArtifactService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/artifacts")
@RestController
public class ArtifactController {

    @Autowired
    private ArtifactService artifactService;

    /**
     * 单件详情查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Artifact> getInfo(@PathVariable Integer id){
        log.info("单件查询：{}", id);
        Artifact artifact = artifactService.getInfo(id);
        return artifact == null ? Result.error("未找到该artifact") : Result.success(artifact);
    }

    /**
     * 分页查询
     * @param param
     * @return
     */
    @GetMapping
    public Result<PageResult<Artifact>> page(ArtifaQueryParam  param){
        log.info("分页查询：{}", param);
        PageResult<Artifact> pageResult = artifactService.page(param);
        return Result.success(pageResult);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result<Void> delete(@RequestParam("ids") List<Integer> ids){
        log.info("删除：{}", ids);
        artifactService.delete(ids);
        return Result.success();
    }

    /**
     * 添加
     * @param artifact
     * @return
     */
    @PostMapping
    public Result<Void> add(@RequestBody Artifact artifact){
        log.info("添加：{}", artifact);
        artifactService.add(artifact);
        return Result.success();
    }

    @PutMapping
    public Result<Void> update(@RequestBody Artifact artifact){
        log.info("更新：{}", artifact);
        artifactService.update(artifact);
        return Result.success();
    }

}
