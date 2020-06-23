const webpack       = require('webpack');
const path          = require('path');

const assetsDir   = path.join(__dirname, 'public/assets');
const vendorsDir  = path.join(__dirname, 'app/vendors');
const srcInclude  = path.join(__dirname, 'app');
const indexFile   = path.join(__dirname, 'app/index.jsx');

const config = {
  devtool: 'cheap-module-source-map',
  entry: [
    'react-hot-loader/patch',
    'webpack-hot-middleware/client',
    indexFile
  ],
  output: {
    path:       assetsDir,
    filename:   'bundle.js',
    publicPath: '/public/assets/'
  },
  resolve: {
    extensions: ['.jsx', '.js']
  },
  module: {
    rules: [
      {
        test:     /\.jsx?$/,
        include:  srcInclude,
        exclude:  [vendorsDir],
        loaders:  ['babel-loader']
      },
      {
        test: /\.css$/,
        use:  [
          'style-loader',
          {loader: 'css-loader', options: { importLoaders: 1 }},
          {loader: 'postcss-loader', options: { sourceMap: true}}
        ]
      },
      {
        test:  /\.scss$/,
        use:  [
          'style-loader',
          {loader: 'css-loader', options: { sourceMap: true, importLoaders: 1 }},
          {loader: 'postcss-loader', options: { sourceMap: true}},
          {loader: 'sass-loader', options: { sourceMap: true }}
        ]
      },
      {
        test: /\.(eot|woff|woff2|ttf|svg|png|jpe?g|gif)(\?\S*)?$/,
        use: [
          {
            loader:  'url-loader',
            options: {
              limit: 100000,
              name: '[name].[ext]'
            }
          }
        ]
      }
    ]
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new webpack.NoEmitOnErrorsPlugin(),
    getImplicitGlobals(),
    setNodeEnv()
  ]
};
/*
* here using hoisting so don't use `var NAME = function()...`
*/
function getImplicitGlobals() {
  return new webpack.ProvidePlugin({
    $: 'jquery',
    jQuery: 'jquery'
  });
}

function setNodeEnv() {
  return new webpack.DefinePlugin({
    'process.env': {
      'NODE_ENV': JSON.stringify('dev')
    }
  });
}

module.exports = config;
