package com.test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class GenerateConfiguration {
    private ConfigurationBean configurationBean;

    public ConfigurationBean getConfigurationBean() {
        return configurationBean;
    }

    public void setConfigurationBean(ConfigurationBean configurationBean) {
        this.configurationBean = configurationBean;
    }

    public void creatConfiguration() {
        configurationBean = new ConfigurationBean();
    }

    public void initConfiguration(String init_data) {
        try {
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(new FileReader(init_data));
            System.out.println(object);
            String src_project_path = object.get("src_project_path").getAsString();
            String dest_project_path = object.get("dest_project_path").getAsString();
            String dest_project_name = object.get("dest_project_name").getAsString();
            String consensus = object.get("consensus").getAsString();
            String crypto_config_path = object.get("crypto_config_path").getAsString();
            String channel_artifacts_path = object.get("channel_artifacts_path").getAsString();
            String scripts_path = object.get("scripts_path").getAsString();
            String chaincode_path = object.get("chaincode_path").getAsString();
            String env_path = object.get("env_path").getAsString();
            JsonArray container_groups = object.getAsJsonArray("container_groups");
            //解析ansible主控主机项目路径
            configurationBean.setSrc_project_path(src_project_path);
            //解析远方主机项目路径
            configurationBean.setDest_project_path(dest_project_path);
            //解析远方主机项目名
            configurationBean.setDest_project_name(dest_project_name);
            //解析共识方式
            configurationBean.setConsensus(consensus);
            //解析证书存储路径
            configurationBean.setCrypto_config_path(crypto_config_path);
            //解析通道及创世区块路径
            configurationBean.setChannel_artifacts_path(channel_artifacts_path);
            //解析链码存储路径
            configurationBean.setScripts_path(scripts_path);
            //解析测试脚本路径
            configurationBean.setChaincode_path(chaincode_path);
            configurationBean.setEnv_path(env_path);
            List<ConfigurationBean.ContainerGroupsBean> containerGroupsBeanArrayList = new ArrayList<>();
            for (int i = 0; i < container_groups.size(); i++) {
                ConfigurationBean.ContainerGroupsBean containerGroupsBean = new ConfigurationBean.ContainerGroupsBean();
                String container_name = container_groups.get(i).getAsJsonObject().get("name").getAsString();
                containerGroupsBean.setName(container_name);
                List<ConfigurationBean.ContainerGroupsBean.ContainMemberBean> containMemberBeanList = new ArrayList<>();
                for (int j = 0; j < container_groups.get(i).getAsJsonObject().get("contain_member").getAsJsonArray().size(); j++) {
                    ConfigurationBean.ContainerGroupsBean.ContainMemberBean containMemberBean = new ConfigurationBean.ContainerGroupsBean.ContainMemberBean();
//                        System.out.println(container_groups.get(i).getAsJsonObject().get("contain_member").getAsJsonArray().get(j).getAsJsonObject().get("name"));
                    containMemberBean.setName(container_groups.get(i).getAsJsonObject().get("contain_member").getAsJsonArray().get(j).getAsJsonObject().get("name").getAsString());
                    containMemberBean.setDest(container_groups.get(i).getAsJsonObject().get("contain_member").getAsJsonArray().get(j).getAsJsonObject().get("dest").getAsString());
                    containMemberBean.setFile(container_groups.get(i).getAsJsonObject().get("contain_member").getAsJsonArray().get(j).getAsJsonObject().get("file").getAsString());
                    containMemberBeanList.add(containMemberBean);
                    containerGroupsBean.setContain_member(containMemberBeanList);
                }
                containerGroupsBeanArrayList.add(containerGroupsBean);
            }
            configurationBean.setContainer_groups(containerGroupsBeanArrayList);
            System.out.println(containerGroupsBeanArrayList.get(3).getContain_member().get(0).getName());
//            System.out.println(container_groups.get(0).getAsJsonObject().get("contain_member").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());
//            System.out.println(container_groups.get(0).getAsJsonObject().get("contain_member").getAsJsonArray().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
