"use strict";

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = void 0;
var _react = _interopRequireWildcard(require("react"));
var _reactNative = require("react-native");
var _jsxRuntime = require("react/jsx-runtime");
function _getRequireWildcardCache(e) { if ("function" != typeof WeakMap) return null; var r = new WeakMap(), t = new WeakMap(); return (_getRequireWildcardCache = function (e) { return e ? t : r; })(e); }
function _interopRequireWildcard(e, r) { if (!r && e && e.__esModule) return e; if (null === e || "object" != typeof e && "function" != typeof e) return { default: e }; var t = _getRequireWildcardCache(r); if (t && t.has(e)) return t.get(e); var n = { __proto__: null }, a = Object.defineProperty && Object.getOwnPropertyDescriptor; for (var u in e) if ("default" !== u && {}.hasOwnProperty.call(e, u)) { var i = a ? Object.getOwnPropertyDescriptor(e, u) : null; i && (i.get || i.set) ? Object.defineProperty(n, u, i) : n[u] = e[u]; } return n.default = e, t && t.set(e, n), n; }
// Define the type for the props

const MyForm = ({
  onSubmit
}) => {
  const [name, setName] = (0, _react.useState)('');
  const [email, setEmail] = (0, _react.useState)('');
  const [message, setMessage] = (0, _react.useState)('');
  const handleSubmit = () => {
    if (onSubmit) {
      onSubmit({
        name,
        email,
        message
      });
    }
  };
  return /*#__PURE__*/(0, _jsxRuntime.jsxs)(_reactNative.View, {
    style: styles.container,
    children: [/*#__PURE__*/(0, _jsxRuntime.jsx)(_reactNative.Text, {
      style: styles.label,
      children: "Name:"
    }), /*#__PURE__*/(0, _jsxRuntime.jsx)(_reactNative.TextInput, {
      style: styles.input,
      value: name,
      onChangeText: setName
    }), /*#__PURE__*/(0, _jsxRuntime.jsx)(_reactNative.Text, {
      style: styles.label,
      children: "Email:"
    }), /*#__PURE__*/(0, _jsxRuntime.jsx)(_reactNative.TextInput, {
      style: styles.input,
      value: email,
      onChangeText: setEmail,
      keyboardType: "email-address"
    }), /*#__PURE__*/(0, _jsxRuntime.jsx)(_reactNative.Text, {
      style: styles.label,
      children: "Message:"
    }), /*#__PURE__*/(0, _jsxRuntime.jsx)(_reactNative.TextInput, {
      style: styles.input,
      value: message,
      onChangeText: setMessage,
      multiline: true
    }), /*#__PURE__*/(0, _jsxRuntime.jsx)(_reactNative.Button, {
      title: "Submit",
      onPress: handleSubmit
    })]
  });
};
const styles = _reactNative.StyleSheet.create({
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
var _default = exports.default = MyForm;
//# sourceMappingURL=MyForm.js.map