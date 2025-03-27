"use strict";

import React, { useState } from 'react';
import { View, TextInput, Button, Text, StyleSheet } from 'react-native';

// Define the type for the props
import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
const MyForm = ({
  onSubmit
}) => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [message, setMessage] = useState('');
  const handleSubmit = () => {
    if (onSubmit) {
      onSubmit({
        name,
        email,
        message
      });
    }
  };
  return /*#__PURE__*/_jsxs(View, {
    style: styles.container,
    children: [/*#__PURE__*/_jsx(Text, {
      style: styles.label,
      children: "Name:"
    }), /*#__PURE__*/_jsx(TextInput, {
      style: styles.input,
      value: name,
      onChangeText: setName
    }), /*#__PURE__*/_jsx(Text, {
      style: styles.label,
      children: "Email:"
    }), /*#__PURE__*/_jsx(TextInput, {
      style: styles.input,
      value: email,
      onChangeText: setEmail,
      keyboardType: "email-address"
    }), /*#__PURE__*/_jsx(Text, {
      style: styles.label,
      children: "Message:"
    }), /*#__PURE__*/_jsx(TextInput, {
      style: styles.input,
      value: message,
      onChangeText: setMessage,
      multiline: true
    }), /*#__PURE__*/_jsx(Button, {
      title: "Submit",
      onPress: handleSubmit
    })]
  });
};
const styles = StyleSheet.create({
  container: {
    padding: 20
  },
  label: {
    fontSize: 16,
    marginBottom: 5
  },
  input: {
    borderWidth: 1,
    padding: 10,
    marginBottom: 10,
    borderRadius: 5
  }
});
export default MyForm;
//# sourceMappingURL=MyForm.js.map