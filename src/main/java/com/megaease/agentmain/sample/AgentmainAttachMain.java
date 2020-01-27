package com.megaease.agentmain.sample;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

public class AgentmainAttachMain {

    public static void main(String[] args) throws Exception {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor descriptor : list) {
            if (descriptor.displayName().endsWith("AgentTargetSample")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(descriptor.id());
                virtualMachine.loadAgent("C:\\Documents\\github\\instrumentation_explore\\target\\instrumentation_explore-1.0-SNAPSHOT.jar", "hi James");
                virtualMachine.detach();
            }
        }
    }

}
