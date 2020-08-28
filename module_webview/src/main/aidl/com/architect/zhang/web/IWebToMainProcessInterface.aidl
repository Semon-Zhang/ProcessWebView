// IWebToMainProcessInterface.aidl
package com.architect.zhang.web;

// Declare any non-default types here with import statements
import com.architect.zhang.web.ICallbackMainToWebProcessInterface;
interface IWebToMainProcessInterface {
  void handlerWebCommand(String commandName,String jsonParams, in ICallbackMainToWebProcessInterface callback);
}
