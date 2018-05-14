const webpack = require('webpack');
const BabiliPlugin = require('babili-webpack-plugin');
const path = require('path');
const cssnano = require('cssnano');
const ExtractTextPlugin = require('extract-text-webpack-plugin');
const OptimizeCSSAssetsPlugin = require('optimize-css-assets-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');

const cssplugin = new ExtractTextPlugin({
    filename: 'stylesheets/[name]-css.css',
});

const autoprefix = () => ({
    loader: 'postcss-loader',
    options: {
        plugins: () => ([
        require('autoprefixer'),
    ]),
},
});

module.exports = {
    entry: {
        app:'./src/main/react/app.js',
    },
    devtool: 'sourcemaps',
    output: {
        path: path.join(__dirname,"src/main/webapp/resources"),
        filename: 'built/bundle.js'
    },
    devServer: {
        // contentBase: [path.join(__dirname, "src/main/webapp"),path.join(__dirname, "src/main/webapp/WEB-INF/html")],
        historyApiFallback:true,
        compress: true
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /(node_modules)/,
                include:path.resolve(__dirname,"src/main/react"),
                loader: 'babel-loader',
                options: {
                    cacheDirectory: true,
                    presets: ['stage-0','es2015', 'react']
                }
            },
            {
                test: /\.css$/,
                use: cssplugin.extract({
                    use: ['css-loader', autoprefix()],
                    fallback: 'style-loader',
                }),
            },
            {
                test: /\.less$/,
                use: [
                    {loader: "style-loader"},
                    {loader: "css-loader"},
                    {loader: "less-loader"}
                ]

            },
            {
                test: /\.(png|jpg|svg|jpeg|gif)$/,
                use: {
                    loader: 'url-loader',
                    options:{
                        limit:10000,
                        name: 'images/[name].[hash:8].[ext]',
                    },
                },
            },
        ]
    },
    plugins:[
        cssplugin,
        new HtmlWebpackPlugin({
            chunks:["app"],
            filename: "index.html",
            template:require.resolve(
                './index.ejs'
            ),
            title:"首页",
        }),
        new OptimizeCSSAssetsPlugin({
            cssProcessor: cssnano,
            cssProcessorOptions: {
                discardComments: {
                    removeAll: true,
                    safe: true,
                }
            },
            canPrint: false,
        }),
    ]
};