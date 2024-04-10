package com.heartbeat.myapp.dp.identifier;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class PermissionId implements Identifier<Integer> {

    private final Integer value;

    public PermissionId(Integer value) {
        this.value = value;
    }

    /**
     * @param permissionIds int权限ID列表
     * @return List<PermissionId>
     */
    public static List<PermissionId> toList(List<Integer> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Collections.emptyList();
        }

        List<PermissionId> permissionIdList = new ArrayList<>();
        permissionIds.forEach(permissionId -> {
            permissionIdList.add(new PermissionId(permissionId));
        });

        return permissionIdList;
    }
}
