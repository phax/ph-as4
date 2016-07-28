package com.helger.as4server.client;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.xml.datatype.DatatypeConfigurationException;

import org.w3c.dom.Document;

import com.helger.as4lib.ebms3header.Ebms3Error;
import com.helger.as4lib.ebms3header.Ebms3MessageInfo;
import com.helger.as4lib.ebms3header.Ebms3Messaging;
import com.helger.as4lib.ebms3header.Ebms3SignalMessage;
import com.helger.as4lib.marshaller.Ebms3WriterBuilder;
import com.helger.as4lib.soap11.Soap11Body;
import com.helger.as4lib.soap11.Soap11Envelope;
import com.helger.as4lib.soap11.Soap11Header;
import com.helger.commons.collection.ext.CommonsArrayList;
import com.helger.commons.collection.ext.ICommonsList;

public class CreateErrorMessage
{
  private final ICommonsList <Ebms3Error> m_aErrorMessages = new CommonsArrayList<> ();

  public Document createErrorMessage (@Nonnull final Ebms3MessageInfo aEbms3MessageInfo,
                                      @Nullable final ICommonsList <Ebms3Error> aErrorMessages) throws DatatypeConfigurationException
  {
    // Creating SOAP
    final Soap11Envelope aSoapEnv = new Soap11Envelope ();
    aSoapEnv.setHeader (new Soap11Header ());
    aSoapEnv.setBody (new Soap11Body ());

    // Creating Message
    final Ebms3Messaging aMessage = new Ebms3Messaging ();
    // TODO needs to be set to false because holodeck throws error if it is set
    // to true
    aMessage.setS11MustUnderstand (false);
    final Ebms3SignalMessage aSignalMessage = new Ebms3SignalMessage ();

    // Message Info
    aSignalMessage.setMessageInfo (aEbms3MessageInfo);

    if (aErrorMessages.isNotEmpty ())
      m_aErrorMessages.addAll (aErrorMessages);

    // Error Message
    if (m_aErrorMessages.isNotEmpty ())
      aSignalMessage.setError (m_aErrorMessages);

    aMessage.addSignalMessage (aSignalMessage);

    // Adding the signal message to the existing soap
    final Document aEbms3Message = Ebms3WriterBuilder.ebms3Messaging ().getAsDocument (aMessage);
    aSoapEnv.getHeader ().addAny (aEbms3Message.getDocumentElement ());

    return Ebms3WriterBuilder.soap11 ().getAsDocument (aSoapEnv);
  }

  public Ebms3MessageInfo createEbms3MessageInfo (final String sMessageId) throws DatatypeConfigurationException
  {
    return MessageHelperMethods.createEbms3MessageInfo (sMessageId, null);
  }

  public void addEbms3Error (final Ebms3Error aEbms3Error)
  {
    m_aErrorMessages.add (aEbms3Error);
  }
}
