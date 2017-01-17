/**
 * Copyright (C) 2015-2017 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.as4server.settings;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import com.helger.as4lib.model.pmode.config.DefaultPModeConfigResolver;
import com.helger.as4lib.model.pmode.config.IPModeConfigResolver;
import com.helger.commons.ValueEnforcer;
import com.helger.commons.annotation.Nonempty;

@NotThreadSafe
public class AS4ServerSettings
{
  private final static String DEFAULT_RESPONDER_ID = "default";

  private static String m_sResponderID = DEFAULT_RESPONDER_ID;
  private static IPModeConfigResolver s_aPModeConfigResolver = new DefaultPModeConfigResolver ();

  private AS4ServerSettings ()
  {}

  @Nonnull
  @Nonempty
  public static String getDefaultResponderID ()
  {
    return m_sResponderID;
  }

  public static void setDefaultResponderID (@Nonnull @Nonempty final String sResponderID)
  {
    ValueEnforcer.notEmpty (sResponderID, "ResponderID");
    m_sResponderID = sResponderID;
  }

  @Nonnull
  public static IPModeConfigResolver getPModeConfigResolver ()
  {
    return s_aPModeConfigResolver;
  }

  public static void setPModeConfigResolver (@Nonnull final IPModeConfigResolver aPModeConfigResolver)
  {
    ValueEnforcer.notNull (aPModeConfigResolver, "PModeConfigResolver");
    s_aPModeConfigResolver = aPModeConfigResolver;
  }
}