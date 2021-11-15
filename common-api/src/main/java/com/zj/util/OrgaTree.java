package com.zj.util;

import com.zj.entity.Organize;
import com.zj.entity.Perm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/15 9:50
 */
public class OrgaTree {
    private List<Organize> permList;
    public OrgaTree(List<Organize> permList){
        this.permList = permList;
    }

    public List<Organize> buildTree(){
        List<Organize> treePerms = new ArrayList<Organize>();
        for (Organize permNode : getRootNode()){
            permNode = buildChildTree(permNode);
            treePerms.add(permNode);
        }
        return treePerms;
    }

    private Organize buildChildTree(Organize pNode){
        List<Organize> childPerms = new ArrayList<Organize>();
        for (Organize permNode : permList){
            if (permNode.getParentId().equals(pNode.getId())){
                childPerms.add(buildChildTree(permNode));
            }
        }
        pNode.setChildren(childPerms);
        return pNode;
    }

    private List<Organize> getRootNode() {
        List<Organize> rootPermList = new ArrayList<Organize>();
        for (Organize permNode : permList){
            if (permNode.getParentId().equals(0)){
                rootPermList.add(permNode);
            }
        }
        return rootPermList;
    }
}
