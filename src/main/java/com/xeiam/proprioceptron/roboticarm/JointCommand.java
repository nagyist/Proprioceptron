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

/**
 * @author timmolter
 * @create Sep 28, 2012
 */
public final class JointCommand {

  private final int jointNumber;
  private final int direction;
  public float distance;

  /**
   * Constructor
   * 
   * @param jointNumber
   * @param direction
   * @param steps
   */
  public JointCommand(int jointNumber, int direction, float distance) {

    this.jointNumber = jointNumber;
    this.direction = direction;
    this.distance = distance;
  }

  /**
   * @return the jointNumber
   */
  public int getJointNumber() {

    return jointNumber;
  }

  /**
   * @return the direction
   */
  public int getDirection() {

    return direction;
  }
}
