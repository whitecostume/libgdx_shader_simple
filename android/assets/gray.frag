#ifdef GL_ES
precision mediump float;
#endif
varying vec2 v_texCoords;
varying vec4 v_color;
uniform sampler2D u_texture;


void main()
{
    vec4 v = texture2D(u_texture, v_texCoords);
    float f = v.r * 0.299 + v.g * 0.587 + v.b * 0.114;
    gl_FragColor = vec4(f, f, f, v.a*v_color.a);
}
