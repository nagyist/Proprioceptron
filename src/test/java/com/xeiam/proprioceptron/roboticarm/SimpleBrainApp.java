/**
 * Copyright 2012 MANC LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xeiam.proprioceptron.roboticarm;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.jme3.system.AppSettings;

/**
 * Run the Robotic Arm game with a simple test brain completing the feedback loop
 * 
 * @author timmolter
 * @create Sep 28, 2012
 */
public class SimpleBrainApp implements PropertyChangeListener {

  private static final int NUM_JOINTS = 2;

  private final SimpleBrain simpleBrain;
  private final RoboticArm roboticArm;

  /**
   * Constructor
   */
  public SimpleBrainApp() {

    simpleBrain = new SimpleBrain();

    roboticArm = new RoboticArm(NUM_JOINTS);
    roboticArm.setShowSettings(false);
    AppSettings settings = new AppSettings(true);
    settings.setResolution(480, 480);
    settings.setTitle("Proprioceptron - Simple Test Brain");
    roboticArm.setSettings(settings);
    roboticArm.setEnableKeys(true);
    roboticArm.addChangeListener(this);
    roboticArm.start();

  }

  public static void main(String[] args) {

    SimpleBrainApp stm = new SimpleBrainApp();
  }

  @Override
  public void propertyChange(PropertyChangeEvent pce) {

    List<JointCommand> jointCommands = simpleBrain.update(pce);

    // System.out.println(newEnvState.getDistHead());

    roboticArm.moveJoints(jointCommands);
  }

  private class SimpleBrain {

    private final Random random = new Random();

    /**
     * @param pce
     */
    public List<JointCommand> update(PropertyChangeEvent pce) {

      EnvState oldEnvState = (EnvState) pce.getOldValue();
      EnvState newEnvState = (EnvState) pce.getNewValue();

      List<JointCommand> jointCommands = new ArrayList<JointCommand>();

      // simulate a pause
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      int numJoints = newEnvState.getRelativePositions().length;
      for (int i = 0; i < numJoints; i++) {
        jointCommands.add(new JointCommand(i, random.nextDouble() > 0.5 ? 1 : -1, random.nextInt(50)));
      }

      return jointCommands;

    }
  }
}