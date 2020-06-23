const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const autoprefixer = require('autoprefixer');


module.exports = {
  entry: './app/index.jsx',
  module: {
	    rules: [
	      {
	        test: /\.(js|jsx)$/,
	        exclude: /node_modules/,
	        use: ['babel-loader']
	      },
	      {
	    	  test: /\.s?css$/,
	    	  use: [
	    	    MiniCssExtractPlugin.loader,
	    	    {
	    	      loader: 'css-loader',
	    	      options: {
	    	        minimize: {
	    	          safe: true
	    	        }
	    	      }
	    	    },
	    	    {
	    	      loader: 'postcss-loader',
	    	      options: {
	    	        plugins: () => [
	    	          require('postcss-flexbugs-fixes'),
	    	          autoprefixer({}),
	    	        ],
	    	      },
	    	    },
	    	    'sass-loader',
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
	  resolve: {
	    extensions: ['*', '.js', '.jsx']
	  },
  output: {
    path: __dirname + '/../../WebContent',
    publicPath: '../../WebContent',
    filename: 'bundle.js'
  },
  plugins: [
	    new webpack.HotModuleReplacementPlugin(),
	    new MiniCssExtractPlugin() 
	  ],
  devServer: {
    contentBase: '../../WebContent',
    hot: true
  }
};