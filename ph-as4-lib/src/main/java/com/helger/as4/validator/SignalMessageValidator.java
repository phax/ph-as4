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
package com.helger.as4.validator;

import javax.annotation.Nonnull;

import com.helger.as4.error.EEbmsError;
import com.helger.as4lib.ebms3header.Ebms3SignalMessage;

/**
 * Not sure if needed since xsd checks all of the following
 *
 * @author bayerlma
 */
public class SignalMessageValidator
{
  public void validateSignalMessage (@Nonnull final Ebms3SignalMessage aSignalMessage) throws Ebms3ValidationException
  {
    if (aSignalMessage.getMessageInfo ().getMessageId ().isEmpty ())
      throw new Ebms3ValidationException (EEbmsError.EBMS_INVALID_HEADER, "MessageInfo messageId is missing", null);

    final String sRefToMessageId = aSignalMessage.getMessageInfo ().getRefToMessageId ();
    if (!aSignalMessage.getError ().isEmpty () ||
        !aSignalMessage.getPullRequest ().getMpc ().isEmpty () ||
        aSignalMessage.getReceipt ().hasAnyEntries ())
    {

    }
    else
    {
      throw new Ebms3ValidationException (EEbmsError.EBMS_INVALID_HEADER,
                                "No Messages are inside the SignalMessage",
                                sRefToMessageId);
    }
  }
}