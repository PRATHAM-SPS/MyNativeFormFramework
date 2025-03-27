module.exports = {
  presets: [
    'module:metro-react-native-babel-preset',
    '@babel/preset-typescript',
    '@babel/preset-flow'
  ],
  plugins: [
    '@babel/plugin-transform-flow-strip-types', // Ensure Flow types are stripped
    '@babel/plugin-proposal-class-properties',
    '@babel/plugin-transform-runtime'
  ],
};
