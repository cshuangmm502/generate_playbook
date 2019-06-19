package com.test;

public class test {
    public static void main(String args[]){
        String init_data="src//com//test/data.json";
        GenerateConfiguration configuration = new GenerateConfiguration();
        configuration.creatConfiguration();
        configuration.initConfiguration(init_data);
        Generate_Playbook playbook = new Generate_Playbook(configuration.getConfigurationBean());
        playbook.creatPlaybook();
        playbook.writePlaybook();

    }

}

