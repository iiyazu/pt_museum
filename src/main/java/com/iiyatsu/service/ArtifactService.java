package com.iiyatsu.service;

import com.iiyatsu.pojo.ArtifaQueryParam;
import com.iiyatsu.pojo.PageResult;
import com.iiyatsu.pojo.artifact.Artifact;

import java.util.List;

public interface ArtifactService {
    Artifact getInfo(Integer id);

    PageResult<Artifact> page(ArtifaQueryParam param);

    void delete(List<Integer> ids);

    void add(Artifact artifact);

    void update(Artifact artifact);
}
