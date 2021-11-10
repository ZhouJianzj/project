package com.zj.util;

import com.zj.entity.Perm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HuXinJie
 * @version 1.0
 * @date 2021/11/10 12:30
 */
public class ListToTree {
    private List<Perm> permList;
    public ListToTree(List<Perm> permList){
        this.permList = permList;
    }

    public List<Perm> buildTree(){
        List<Perm> treePerms = new ArrayList<Perm>();
        for (Perm permNode : getRootNode()){
            permNode = buildChildTree(permNode);
            treePerms.add(permNode);
        }
        return treePerms;
    }

    private Perm buildChildTree(Perm pNode){
        List<Perm> childPerms = new ArrayList<Perm>();
        for (Perm permNode : permList){
            if (permNode.getParentId().equals(pNode.getId())){
                childPerms.add(buildChildTree(permNode));
            }
        }
        pNode.setChildren(childPerms);
        return pNode;
    }

    private List<Perm> getRootNode() {
        List<Perm> rootPermList = new ArrayList<Perm>();
        for (Perm permNode : permList){
            if (permNode.getParentId().equals(0)){
                rootPermList.add(permNode);
            }
        }
        return rootPermList;
    }
}
