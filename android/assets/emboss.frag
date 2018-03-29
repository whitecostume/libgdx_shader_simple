#ifdef GL_ES
precision mediump float;
#endif
varying vec2 v_texCoords;
uniform float widthStep;
uniform float heightStep;
uniform sampler2D u_texture;
const float stride = 2.0;
void main()
{
    vec3 tmpColor = texture2D(u_texture, v_texCoords + vec2(widthStep * stride, heightStep * stride)).rgb;
    tmpColor = texture2D(u_texture, v_texCoords).rgb - tmpColor + 0.5;
    float f = (tmpColor.r + tmpColor.g + tmpColor.b) / 3.0;
    gl_FragColor = vec4(f, f, f, 1.0);
}