const { getDefaultConfig } = require('@react-native/metro-config');

module.exports = (async () => {
  const defaultConfig = await getDefaultConfig(__dirname);
  return {
    ...defaultConfig,
    resolver: {
      ...defaultConfig.resolver,
      sourceExts: ['js', 'jsx', 'ts', 'tsx', 'json'], // Ensure TSX and JSX files are processed
    },
    transformer: {
      ...defaultConfig.transformer,
      babelTransformerPath: require.resolve('metro-react-native-babel-transformer'),
    },
  };
})();
