package com.megaease.agentmain.sample;

public class AgentTargetSample {

    public static void main(String[] args) throws Exception {
        AgentMainService agentMainService = new AgentMainService();
        while (true) {
            Thread.sleep(1000);
            agentMainService.doService();
        }
    }

}
