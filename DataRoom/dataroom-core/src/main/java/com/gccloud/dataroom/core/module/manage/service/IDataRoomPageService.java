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

package com.gccloud.dataroom.core.module.manage.service;

import com.gccloud.dataroom.core.module.basic.dto.BasePageDTO;
import com.gccloud.dataroom.core.module.basic.entity.PageEntity;
import com.gccloud.dataroom.core.module.basic.service.IBasePageService;
import com.gccloud.dataroom.core.module.manage.dto.DataRoomPageDTO;
import com.gccloud.dataroom.core.module.manage.dto.DataRoomSearchDTO;
import com.gccloud.common.vo.PageVO;

/**
 * @author hongyang
 * @version 1.0
 * @date 2023/3/13 10:59
 */
public interface IDataRoomPageService extends IBasePageService {

    /**
     * 从空白新增页面
     *
     * @param pageDTO
     * @return
     */
    String add(BasePageDTO pageDTO);


    /**
     * 从模板新增页面
     *
     * @param pageDTO
     * @return
     */
    String addByTemplate(BasePageDTO pageDTO);

    /**
     * 根据编码获取页面配置
     *
     * @param basePageDTO
     * @return
     */
    BasePageDTO getConfigByTemplate(BasePageDTO basePageDTO);

    /**
     * 分页查询
     *
     * @param searchDTO
     * @return
     */
    PageVO<PageEntity> getByCategory(DataRoomSearchDTO searchDTO);

    /**
     * 更新页面
     *
     * @param pageDTO
     */
    void update(BasePageDTO pageDTO);

    /**
     * 复制页面
     * @param entity
     * @return
     */
    String copy(PageEntity entity);

    /**
     * 根据编码删除
     *
     * @param code
     */
    void deleteByCode(String code);

}