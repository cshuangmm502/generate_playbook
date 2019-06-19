package com.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Generate_Playbook {
    private ConfigurationBean configuration;
    private String play_book_path;

    public Generate_Playbook(ConfigurationBean configuration) {
        setConfiguration(configuration);
    }

    public void setConfiguration(ConfigurationBean configuration) {
        this.configuration = configuration;
    }

    public ConfigurationBean getConfiguration() {
        return configuration;
    }

    public void setplay_book_path(String play_book_path) {
        this.play_book_path = play_book_path;
    }

    public String getplay_book_path() {
        return play_book_path;
    }

    public boolean creatPlaybook() {
        Boolean bool = false;
        String file_path = System.getProperty("user.dir");
        String file_name = "ansible_file";
        String file_temp = file_path + "/" + file_name + ".yaml";
        File file = new File(file_temp);
        try {
            if (!file.exists()) {
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is " + file_temp);
            } else {
                file.delete();
                file.createNewFile();
                bool = true;
                System.out.println("success create file,the file is " + file_temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setplay_book_path(file_temp);
        return bool;
    }

    public boolean writePlaybook() {
        boolean bool = false;
        String new_lines = "\n";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos = null;
        PrintWriter pw = null;

        try {
            File file = new File(getplay_book_path());
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            buffer.append(write_Common_Option());
            buffer.append(write_Zookeeper_Group());
            buffer.append(write_Kafka_Group());
            buffer.append(write_Orderer_Group());
            buffer.append(write_Peer_Group());
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bool;
    }

    public StringBuffer write_Blackspace(int num) {
        StringBuffer nBuffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            nBuffer.append(" ");
        }
        return nBuffer;
    }

    public StringBuffer write_Common_Option() {
        StringBuffer buffer = new StringBuffer();
        String new_lines = "\n";

        /*- hosts: all
                name: common option in all computer
                task1-1: creat project directory in all*/
        buffer.append("---");
        buffer.append(new_lines);
        buffer.append("-").append(write_Blackspace(1)).append("hosts:").append(write_Blackspace(1))
                .append("all").append(new_lines);
        buffer.append(write_Blackspace(2)).append("name:").append(write_Blackspace(1))
                .append("common option in all computer").append(new_lines);
        buffer.append(write_Blackspace(2)).append("remote_user:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(2)).append("tasks:").append(new_lines);
        buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1))
                .append("name:").append(write_Blackspace(1))
                .append("creat project directory in all").append(new_lines);
        buffer.append(write_Blackspace(6)).append("file:").append(new_lines);
        buffer.append(write_Blackspace(8)).append("path:").append(write_Blackspace(1))
                .append(getConfiguration().getDest_project_path()).append("/").append(getConfiguration()
                .getDest_project_name()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("state:").append(write_Blackspace(1))
                .append("directory").append(new_lines);
        buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(new_lines);

        /*task1-2: send crypto-config  to all*/
        buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                .append(write_Blackspace(1)).append("send crypto-config to all").append(new_lines);
        buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
        buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
                .append(getConfiguration().getSrc_project_path()).append("/").append("crypto-config").append(new_lines);
        buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                .append(getConfiguration().getDest_project_path()).append("/")
                .append(getConfiguration().getDest_project_name()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(new_lines);

        /*task1-3: send channel-artifacts to all*/
        buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                .append(write_Blackspace(1)).append("send channel-artifacts to all").append(new_lines);
        buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
        buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
                .append(getConfiguration().getChannel_artifacts_path()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                .append(getConfiguration().getDest_project_path()).append("/")
                .append(getConfiguration().getDest_project_name()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(new_lines);

        /*task1-4: send chaincode to all */
        buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                .append(write_Blackspace(1)).append("send chaincode to all").append(new_lines);
        buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
        buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
               .append(getConfiguration().getChaincode_path()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                .append(getConfiguration().getDest_project_path()).append("/")
                .append(getConfiguration().getDest_project_name()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(new_lines);

        /*task1-5: send scripts to all*/
        buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                .append(write_Blackspace(1)).append("send scripts to all").append(new_lines);
        buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
        buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
                .append(getConfiguration().getScripts_path()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                .append(getConfiguration().getDest_project_path()).append("/")
                .append(getConfiguration().getDest_project_name()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(new_lines);

        /*task1-6: send env file to all*/
        buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                .append(write_Blackspace(1)).append("send env to all").append(new_lines);
        buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
        buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
                .append(getConfiguration().getEnv_path()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                .append(getConfiguration().getDest_project_path()).append("/")
                .append(getConfiguration().getDest_project_name()).append(new_lines);
        buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(new_lines);
        return buffer;
    }

    //生成contain_groups部分
//    public StringBuffer write_main_content(){
//        StringBuffer buffer = new StringBuffer();
//        String new_lines = "\n";
//        buffer.append("-").append(write_Blackspace(1)).append("hosts:").append(write_Blackspace(1))
//                .append("zookeeper_group").append(new_lines);
//        buffer.append(write_Blackspace(2)).append("name:").append(write_Blackspace(1))
//                .append("send file to zookeeper_group").append(new_lines);
//        buffer.append(write_Blackspace(2)).append("remote_user:").append(write_Blackspace(1))
//                .append("root").append(new_lines);
//        buffer.append(write_Blackspace(2)).append("tasks:").append(new_lines);
//        List<ConfigurationBean.ContainerGroupsBean> containerGroupsBeanList = new ArrayList<>();
//        containerGroupsBeanList = getConfiguration().getContainer_groups();
//        return buffer;
//    }

    //生成zookeeper部分
    public StringBuffer write_Zookeeper_Group() {
        StringBuffer buffer = new StringBuffer();
        String new_lines = "\n";
        buffer.append("-").append(write_Blackspace(1)).append("hosts:").append(write_Blackspace(1))
                .append("zookeeper_group").append(new_lines);
        buffer.append(write_Blackspace(2)).append("name:").append(write_Blackspace(1))
                .append("send file to zookeeper_group").append(new_lines);
        buffer.append(write_Blackspace(2)).append("remote_user:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(2)).append("tasks:").append(new_lines);
        List<ConfigurationBean.ContainerGroupsBean> containerGroupsBeanList = new ArrayList<>();
        containerGroupsBeanList = getConfiguration().getContainer_groups();
        System.out.println(getConfiguration().getContainer_groups());
        List<ConfigurationBean.ContainerGroupsBean.ContainMemberBean> containMemberBeanList = new ArrayList<>();
        for(int i=0;i<containerGroupsBeanList.size();i++){
            if(containerGroupsBeanList.get(i).getName().equals("zookeeper_group")){
                containMemberBeanList = containerGroupsBeanList.get(i).getContain_member();
                break;
            }
        }
        for(int i=0;i<containMemberBeanList.size();i++){
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("send ").append(containMemberBeanList.get(i).getName())
                    .append(" to ").append(containMemberBeanList.get(i).getDest()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
            buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
                    .append(getConfiguration().getSrc_project_path()).append("/").append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                    .append(getConfiguration().getDest_project_path()).append("/")
                    .append(getConfiguration().getDest_project_name()).append("/").append(new_lines);
            buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                    .append("root").append(new_lines);
            buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                    .append("root").append(new_lines);
            buffer.append(write_Blackspace(6)).append("notify:").append(write_Blackspace(1)).append("up")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("when:").append(write_Blackspace(1))
                    .append("ansible_default_ipv4.address").append(write_Blackspace(1)).append("==")
                    .append(write_Blackspace(1)).append("\"").append(containMemberBeanList.get(i)
                    .getDest()).append("\"").append(new_lines);
            buffer.append(new_lines);
        }
        //生成对应handlers
        buffer.append(write_Blackspace(2)).append("handlers:").append(new_lines);
        for (int i=0;i<containMemberBeanList.size();i++){
            StringBuffer temp_name = new StringBuffer();
            temp_name.append("msg_").append(containMemberBeanList.get(i).getName());
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("up")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("command:").append(write_Blackspace(1))
                    .append("chdir=").append(getConfiguration().getDest_project_path()).append("/")
                    .append(getConfiguration().getDest_project_name()).append(write_Blackspace(1))
                    .append("docker-compose").append(write_Blackspace(1)).append("-f")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(write_Blackspace(1)).append("up").append(write_Blackspace(1))
                    .append("-d").append(new_lines);
            buffer.append(write_Blackspace(6)).append("register:").append(write_Blackspace(1))
                    .append(temp_name).append(new_lines);
            buffer.append(write_Blackspace(6)).append("notify:").append(write_Blackspace(1)).append("show")
                    .append(write_Blackspace(1))
                    .append(temp_name).append(new_lines);
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("show").append(write_Blackspace(1)).append(temp_name)
                    .append(new_lines);
            buffer.append(write_Blackspace(6)).append("debug:").append(write_Blackspace(1)).append("var=")
                    .append(temp_name).append(write_Blackspace(1)).append("verbosity=0").append(new_lines);
            buffer.append(new_lines);
        }

        return buffer;
    }

    //生成kafka部分
    public StringBuffer write_Kafka_Group() {
        StringBuffer buffer = new StringBuffer();
        String new_lines = "\n";
        buffer.append("-").append(write_Blackspace(1)).append("hosts:").append(write_Blackspace(1))
                .append("kafka_group").append(new_lines);
        buffer.append(write_Blackspace(2)).append("name:").append(write_Blackspace(1))
                .append("send file to kafka_group").append(new_lines);
        buffer.append(write_Blackspace(2)).append("remote_user:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(2)).append("tasks:").append(new_lines);
        List<ConfigurationBean.ContainerGroupsBean> containerGroupsBeanList = new ArrayList<>();
        containerGroupsBeanList = getConfiguration().getContainer_groups();
        System.out.println(getConfiguration().getContainer_groups());
        List<ConfigurationBean.ContainerGroupsBean.ContainMemberBean> containMemberBeanList = new ArrayList<>();
        for(int i=0;i<containerGroupsBeanList.size();i++){
            if(containerGroupsBeanList.get(i).getName().equals("kafka_group")){
                containMemberBeanList = containerGroupsBeanList.get(i).getContain_member();
                break;
            }
        }
        for(int i=0;i<containMemberBeanList.size();i++){
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("send ").append(containMemberBeanList.get(i).getName())
                    .append(" to ").append(containMemberBeanList.get(i).getDest()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
            buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
                    .append(getConfiguration().getSrc_project_path()).append("/").append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                    .append(getConfiguration().getDest_project_path()).append("/")
                    .append(getConfiguration().getDest_project_name()).append("/").append(new_lines);
            buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                    .append("root").append(new_lines);
            buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                    .append("root").append(new_lines);
            buffer.append(write_Blackspace(6)).append("notify:").append(write_Blackspace(1)).append("up")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("when:").append(write_Blackspace(1))
                    .append("ansible_default_ipv4.address").append(write_Blackspace(1)).append("==")
                    .append(write_Blackspace(1)).append("\"").append(containMemberBeanList.get(i)
                    .getDest()).append("\"").append(new_lines);
            buffer.append(new_lines);
        }
        //生成对应handlers
        buffer.append(write_Blackspace(2)).append("handlers:").append(new_lines);
        for (int i=0;i<containMemberBeanList.size();i++){
            StringBuffer temp_name = new StringBuffer();
            temp_name.append("msg_").append(containMemberBeanList.get(i).getName());
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("up")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("command:").append(write_Blackspace(1))
                    .append("chdir=").append(getConfiguration().getDest_project_path()).append("/")
                    .append(getConfiguration().getDest_project_name()).append(write_Blackspace(1))
                    .append("docker-compose").append(write_Blackspace(1)).append("-f")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(write_Blackspace(1)).append("up").append(write_Blackspace(1))
                    .append("-d").append(new_lines);
            buffer.append(write_Blackspace(6)).append("register:").append(write_Blackspace(1))
                    .append(temp_name).append(new_lines);
            buffer.append(write_Blackspace(6)).append("notify:").append(write_Blackspace(1))
                    .append("show ")
                    .append(temp_name).append(new_lines);
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("show").append(write_Blackspace(1)).append(temp_name)
                    .append(new_lines);
            buffer.append(write_Blackspace(6)).append("debug:").append(write_Blackspace(1)).append("var=")
                    .append(temp_name).append(write_Blackspace(1)).append("verbosity=0").append(new_lines);
            buffer.append(new_lines);
        }
        return buffer;
    }

    //生成orderer部分
    public StringBuffer write_Orderer_Group() {
        StringBuffer buffer = new StringBuffer();
        String new_lines = "\n";
        buffer.append("-").append(write_Blackspace(1)).append("hosts:").append(write_Blackspace(1))
                .append("orderer_group").append(new_lines);
        buffer.append(write_Blackspace(2)).append("name:").append(write_Blackspace(1))
                .append("send file to orderer_group").append(new_lines);
        buffer.append(write_Blackspace(2)).append("remote_user:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(2)).append("tasks:").append(new_lines);
        List<ConfigurationBean.ContainerGroupsBean> containerGroupsBeanList = new ArrayList<>();
        containerGroupsBeanList = getConfiguration().getContainer_groups();
        System.out.println(getConfiguration().getContainer_groups());
        List<ConfigurationBean.ContainerGroupsBean.ContainMemberBean> containMemberBeanList = new ArrayList<>();
        for(int i=0;i<containerGroupsBeanList.size();i++){
            if(containerGroupsBeanList.get(i).getName().equals("orderer_group")){
                containMemberBeanList = containerGroupsBeanList.get(i).getContain_member();
                break;
            }
        }
        for(int i=0;i<containMemberBeanList.size();i++){
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("send ").append(containMemberBeanList.get(i).getName())
                    .append(" to ").append(containMemberBeanList.get(i).getDest()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
            buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
                    .append(getConfiguration().getSrc_project_path()).append("/").append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                    .append(getConfiguration().getDest_project_path()).append("/")
                    .append(getConfiguration().getDest_project_name()).append("/").append(new_lines);
            buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                    .append("root").append(new_lines);
            buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                    .append("root").append(new_lines);
            buffer.append(write_Blackspace(6)).append("notify:").append(write_Blackspace(1)).append("up")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("when:").append(write_Blackspace(1))
                    .append("ansible_default_ipv4.address").append(write_Blackspace(1)).append("==")
                    .append(write_Blackspace(1)).append("\"").append(containMemberBeanList.get(i)
                    .getDest()).append("\"").append(new_lines);
            buffer.append(new_lines);
        }
        //生成对应handlers
        buffer.append(write_Blackspace(2)).append("handlers:").append(new_lines);
        for (int i=0;i<containMemberBeanList.size();i++){
            StringBuffer temp_name = new StringBuffer();
            temp_name.append("msg_").append(containMemberBeanList.get(i).getName());
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("up")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("command:").append(write_Blackspace(1))
                    .append("chdir=").append(getConfiguration().getDest_project_path()).append("/")
                    .append(getConfiguration().getDest_project_name()).append(write_Blackspace(1))
                    .append("docker-compose").append(write_Blackspace(1)).append("-f")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(write_Blackspace(1)).append("up").append(write_Blackspace(1))
                    .append("-d").append(new_lines);
            buffer.append(write_Blackspace(6)).append("register:").append(write_Blackspace(1))
                    .append(temp_name).append(new_lines);
            buffer.append(write_Blackspace(6)).append("notify:").append(write_Blackspace(1))
                    .append("show ")
                    .append(temp_name).append(new_lines);
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("show").append(write_Blackspace(1)).append(temp_name)
                    .append(new_lines);
            buffer.append(write_Blackspace(6)).append("debug:").append(write_Blackspace(1)).append("var=")
                    .append(temp_name).append(write_Blackspace(1)).append("verbosity=0").append(new_lines);
            buffer.append(new_lines);
        }
        return buffer;
    }

    //生成peer部分
    public StringBuffer write_Peer_Group() {
        StringBuffer buffer = new StringBuffer();
        String new_lines = "\n";
        buffer.append("-").append(write_Blackspace(1)).append("hosts:").append(write_Blackspace(1))
                .append("peer_group").append(new_lines);
        buffer.append(write_Blackspace(2)).append("name:").append(write_Blackspace(1))
                .append("send file to peer_group").append(new_lines);
        buffer.append(write_Blackspace(2)).append("remote_user:").append(write_Blackspace(1))
                .append("root").append(new_lines);
        buffer.append(write_Blackspace(2)).append("tasks:").append(new_lines);
        List<ConfigurationBean.ContainerGroupsBean> containerGroupsBeanList = new ArrayList<>();
        containerGroupsBeanList = getConfiguration().getContainer_groups();
        System.out.println(getConfiguration().getContainer_groups());
        List<ConfigurationBean.ContainerGroupsBean.ContainMemberBean> containMemberBeanList = new ArrayList<>();
        for(int i=0;i<containerGroupsBeanList.size();i++){
            if(containerGroupsBeanList.get(i).getName().equals("peer_group")){
                containMemberBeanList = containerGroupsBeanList.get(i).getContain_member();
                break;
            }
        }
        for(int i=0;i<containMemberBeanList.size();i++){
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("send ").append(containMemberBeanList.get(i).getName())
                    .append(" to ").append(containMemberBeanList.get(i).getDest()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("copy:").append(new_lines);
            buffer.append(write_Blackspace(8)).append("src:").append(write_Blackspace(1))
                    .append(getConfiguration().getSrc_project_path()).append("/").append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(8)).append("dest:").append(write_Blackspace(1))
                    .append(getConfiguration().getDest_project_path()).append("/")
                    .append(getConfiguration().getDest_project_name()).append("/").append(new_lines);
            buffer.append(write_Blackspace(8)).append("owner:").append(write_Blackspace(1))
                    .append("root").append(new_lines);
            buffer.append(write_Blackspace(8)).append("group:").append(write_Blackspace(1))
                    .append("root").append(new_lines);
            buffer.append(write_Blackspace(6)).append("notify:").append(write_Blackspace(1)).append("up")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("when:").append(write_Blackspace(1))
                    .append("ansible_default_ipv4.address").append(write_Blackspace(1)).append("==")
                    .append(write_Blackspace(1)).append("\"").append(containMemberBeanList.get(i)
                    .getDest()).append("\"").append(new_lines);
            buffer.append(new_lines);
        }
        //生成对应handlers
        buffer.append(write_Blackspace(2)).append("handlers:").append(new_lines);
        for (int i=0;i<containMemberBeanList.size();i++){
            StringBuffer temp_name = new StringBuffer();
            temp_name.append("msg_").append(containMemberBeanList.get(i).getName());
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("up")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(new_lines);
            buffer.append(write_Blackspace(6)).append("command:").append(write_Blackspace(1))
                    .append("chdir=").append(getConfiguration().getDest_project_path()).append("/")
                    .append(getConfiguration().getDest_project_name()).append(write_Blackspace(1))
                    .append("docker-compose").append(write_Blackspace(1)).append("-f")
                    .append(write_Blackspace(1)).append(containMemberBeanList.get(i)
                    .getFile()).append(write_Blackspace(1)).append("up").append(write_Blackspace(1))
                    .append("-d").append(new_lines);
            buffer.append(write_Blackspace(6)).append("register:").append(write_Blackspace(1))
                    .append(temp_name).append(new_lines);
            buffer.append(write_Blackspace(6)).append("notify:").append(write_Blackspace(1))
                    .append("show ")
                    .append(temp_name).append(new_lines);
            buffer.append(write_Blackspace(4)).append("-").append(write_Blackspace(1)).append("name:")
                    .append(write_Blackspace(1)).append("show").append(write_Blackspace(1)).append(temp_name)
                    .append(new_lines);
            buffer.append(write_Blackspace(6)).append("debug:").append(write_Blackspace(1)).append("var=")
                    .append(temp_name).append(write_Blackspace(1)).append("verbosity=0").append(new_lines);
            buffer.append(new_lines);
        }
        return buffer;
    }
}
