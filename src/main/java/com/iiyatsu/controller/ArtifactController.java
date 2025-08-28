package com.iiyatsu.controller;

import com.iiyatsu.pojo.ArtifaQueryParam;
import com.iiyatsu.pojo.PageResult;
import com.iiyatsu.pojo.Result;
import com.iiyatsu.pojo.artifact.Artifact;
import com.iiyatsu.service.ArtifactService;
import jakarta.servlet.http.HttpServletRequest;
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
     * 批量删除文物。
     * 这是一个敏感功能，仅限管理员用户执行。
     * @param ids 要删除的文物ID列表，通过请求参数传递
     * @param request HttpServletRequest 用于获取拦截器传递的权限信息
     * @return 成功或失败的响应结果
     */
    @DeleteMapping
    public Result<Void> delete(@RequestParam("ids") List<Integer> ids, HttpServletRequest request) {
        // 从请求属性中获取拦截器传递过来的isAdmin值
        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");

        // 权限校验：如果 isAdmin 为 null 或 false，则表示用户不是管理员
        if (isAdmin == null || !isAdmin) {
            log.warn("非管理员用户尝试执行批量删除文物操作");
            return Result.error("权限不足，此操作仅限管理员");
        }

        log.info("管理员正在批量删除文物，ID列表：{}", ids);
        artifactService.delete(ids);
        return Result.success();
    }

    /**
     * 新增文物。
     * 这是一个敏感功能，仅限管理员用户执行。
     * @param artifact 要新增的文物对象，在请求体中
     * @param request HttpServletRequest 用于获取拦截器传递的权限信息
     * @return 成功或失败的响应结果
     */
    @PostMapping
    public Result<Void> add(@RequestBody Artifact artifact, HttpServletRequest request) {
        // 从请求属性中获取拦截器传递过来的isAdmin值
        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");

        // 权限校验：如果 isAdmin 为 null 或 false，则表示用户不是管理员
        if (isAdmin == null || !isAdmin) {
            log.warn("非管理员用户尝试执行新增文物操作");
            return Result.error("权限不足，此操作仅限管理员");
        }

        log.info("管理员正在新增文物：{}", artifact);
        artifactService.add(artifact);
        return Result.success();
    }

    /**
     * 更新文物信息。
     * 这是一个敏感功能，仅限管理员用户执行。
     * @param artifact 要更新的文物对象，包含要修改的字段和id
     * @param request HttpServletRequest 用于获取拦截器传递的权限信息
     * @return 成功或失败的响应结果
     */
    @PutMapping
    public Result<Void> update(@RequestBody Artifact artifact, HttpServletRequest request){
        // 从请求属性中获取拦截器传递过来的isAdmin值
        Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");

        // 权限校验：如果 isAdmin 为 null 或 false，则表示用户不是管理员
        if (isAdmin == null || !isAdmin) {
            log.warn("非管理员用户尝试执行更新文物操作");
            return Result.error("权限不足，此操作仅限管理员");
        }

        log.info("管理员正在更新文物：{}", artifact);
        artifactService.update(artifact);
        return Result.success();
    }

}
