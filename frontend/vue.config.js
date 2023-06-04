const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: true,
  publicPath: "./",

  configureWebpack() {
    return {
      resolve: {
        fallback: {
          http: require.resolve("stream-http"),
        },
      },
    }
  },
})

// 头部引入
const NodePolyfillPlugin = require('node-polyfill-webpack-plugin')

configureWebpack: (config) => {
	const plugins = []
	plugins.push(new NodePolyfillPlugin())
}




