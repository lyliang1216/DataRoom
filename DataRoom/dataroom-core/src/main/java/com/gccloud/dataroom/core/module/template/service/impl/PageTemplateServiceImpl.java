/*
 * Copyright 2023 http://gcpaas.gccloud.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gccloud.dataroom.core.module.template.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gccloud.dataroom.core.module.basic.dto.BasePageDTO;
import com.gccloud.dataroom.core.module.chart.components.datasource.DataSetDataSource;
import com.gccloud.dataroom.core.module.manage.dto.DataRoomPageDTO;
import com.gccloud.dataroom.core.module.template.dao.DataRoomPageTemplateDao;
import com.gccloud.dataroom.core.module.template.dto.PageTemplateSearchDTO;
import com.gccloud.dataroom.core.module.template.entity.PageTemplateEntity;
import com.gccloud.dataroom.core.module.template.service.IPageTemplateService;
import com.gccloud.common.exception.GlobalException;
import com.gccloud.common.vo.PageVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author hongyang
 * @version 1.0
 * @date 2023/3/20 16:38
 */
@Service("dataRoomPageTemplateService")
public class PageTemplateServiceImpl extends ServiceImpl<DataRoomPageTemplateDao, PageTemplateEntity> implements IPageTemplateService {
    @Override
    public PageVO<PageTemplateEntity> getPage(PageTemplateSearchDTO searchDTO) {
        LambdaQueryWrapper<PageTemplateEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(searchDTO.getType()), PageTemplateEntity::getType, searchDTO.getType());
        return page(searchDTO, queryWrapper);
    }

    @Override
    public List<PageTemplateEntity> getList(PageTemplateSearchDTO searchDTO) {
        LambdaQueryWrapper<PageTemplateEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(searchDTO.getType()), PageTemplateEntity::getType, searchDTO.getType());
        return list(queryWrapper);
    }

    @Override
    public String add(PageTemplateEntity pageTemplate) {
        BasePageDTO config = pageTemplate.getConfig();
        // 清空数据配置
        ((DataRoomPageDTO) config).setId(null);
        List<Map<String, Object>> chartList = ((DataRoomPageDTO) config).getChartList();
        if (chartList != null) {
            for (Map<String, Object> chart : chartList) {
                chart.put("dataSource", new DataSetDataSource());
                chart.put("code", null);
            }
        }
        this.save(pageTemplate);
        return pageTemplate.getId();
    }

    @Override
    public void deleteByIds(List<String> ids) {
        if (ids == null || ids.size() == 0) {
            return;
        }
        this.removeByIds(ids);
    }

    @Override
    public void update(PageTemplateEntity pageTemplate) {
        if (StringUtils.isBlank(pageTemplate.getId())) {
            throw new GlobalException("id不能为空");
        }
        this.updateById(pageTemplate);
    }
}