package com.test;

import java.util.List;

public class ConfigurationBean {


    /**
     * src_project_path : /root/go/fabric-samples
     * dest_project_path : /root/go/fabric-samples
     * dest_project_name : solo-test
     * consensus : kafka
     * container_groups : [{"name":"zookeeper_group","contain_member":[{"name":"zookeeper0","dest":"192.168.1.23","file":"zookeeper0.yaml"},{"name":"zookeeper1","dest":"192.168.1.24","file":"zookeeper1.yaml"}]},{"name":"kafka_group","contain_member":[{"name":"kafka0","dest":"192.168.1.23","file":"kafka0.yaml"},{"name":"kafka1","dest":"192.168.1.24","file":"kafka1.yaml"}]},{"name":"orderer_group","contain_member":[{"name":"orderer0","dest":"192.168.1.23","file":"orderer0.yaml"},{"name":"orderer1","dest":"192.168.1.24","file":"orderer1.yaml"}]},{"name":"peer_group","contain_member":[{"name":"peer0org1","dest":"192.168.1.23","file":"peer0org1.yaml"},{"name":"peer1org1","dest":"192.168.1.24","file":"peer1org1.yaml"},{"name":"peer0org2","dest":"192.168.1.23","file":"peer0org2.yaml"},{"name":"peer1org2","dest":"192.168.1.24","file":"peer1org2.yaml"}]}]
     * crypto_config_path : /root/go/fabric-samples/solo-test/crypto-config
     * channel_artifacts_path : /root/go/fabric-samples/solo-test/crypto-config
     * scripts_path : /root/go/fabric-samples/solo-test/scripts
     * chaincode_path : /root/go/fabric-samples/solo-test/chaincode
     * env_path : /root/go/fabric-samples/solo-test/.env
     */

    private String src_project_path;
    private String dest_project_path;
    private String dest_project_name;
    private String consensus;
    private String crypto_config_path;
    private String channel_artifacts_path;
    private String scripts_path;
    private String chaincode_path;
    private String env_path;
    private List<ContainerGroupsBean> container_groups;

    public String getSrc_project_path() {
        return src_project_path;
    }

    public void setSrc_project_path(String src_project_path) {
        this.src_project_path = src_project_path;
    }

    public String getDest_project_path() {
        return dest_project_path;
    }

    public void setDest_project_path(String dest_project_path) {
        this.dest_project_path = dest_project_path;
    }

    public String getDest_project_name() {
        return dest_project_name;
    }

    public void setDest_project_name(String dest_project_name) {
        this.dest_project_name = dest_project_name;
    }

    public String getConsensus() {
        return consensus;
    }

    public void setConsensus(String consensus) {
        this.consensus = consensus;
    }

    public String getCrypto_config_path() {
        return crypto_config_path;
    }

    public void setCrypto_config_path(String crypto_config_path) {
        this.crypto_config_path = crypto_config_path;
    }

    public String getChannel_artifacts_path() {
        return channel_artifacts_path;
    }

    public void setChannel_artifacts_path(String channel_artifacts_path) {
        this.channel_artifacts_path = channel_artifacts_path;
    }

    public String getScripts_path() {
        return scripts_path;
    }

    public void setScripts_path(String scripts_path) {
        this.scripts_path = scripts_path;
    }

    public String getChaincode_path() {
        return chaincode_path;
    }

    public void setChaincode_path(String chaincode_path) {
        this.chaincode_path = chaincode_path;
    }

    public String getEnv_path() {
        return env_path;
    }

    public void setEnv_path(String env_path) {
        this.env_path = env_path;
    }

    public List<ContainerGroupsBean> getContainer_groups() {
        return container_groups;
    }

    public void setContainer_groups(List<ContainerGroupsBean> container_groups) {
        this.container_groups = container_groups;
    }

    public static class ContainerGroupsBean {
        /**
         * name : zookeeper_group
         * contain_member : [{"name":"zookeeper0","dest":"192.168.1.23","file":"zookeeper0.yaml"},{"name":"zookeeper1","dest":"192.168.1.24","file":"zookeeper1.yaml"}]
         */

        private String name;
        private List<ContainMemberBean> contain_member;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ContainMemberBean> getContain_member() {
            return contain_member;
        }

        public void setContain_member(List<ContainMemberBean> contain_member) {
            this.contain_member = contain_member;
        }

        public static class ContainMemberBean {
            /**
             * name : zookeeper0
             * dest : 192.168.1.23
             * file : zookeeper0.yaml
             */

            private String name;
            private String dest;
            private String file;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDest() {
                return dest;
            }

            public void setDest(String dest) {
                this.dest = dest;
            }

            public String getFile() {
                return file;
            }

            public void setFile(String file) {
                this.file = file;
            }
        }
    }
}
