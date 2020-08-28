// ICallbackMainToWebProcessInterface.aidl
package com.architect.zhang.web;

// Declare any non-default types here with import statements

interface ICallbackMainToWebProcessInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void handlerCallback(String callBackName,String response);
}
