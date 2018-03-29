#ifdef GL_ES
precision mediump float;
#endif
varying vec2 v_texCoords;
uniform sampler2D u_texture;


void main()
{
    vec3 v = texture2D(u_texture, v_texCoords).rgb;
    float f = v.r * 0.299 + v.g * 0.587 + v.b * 0.114;
    gl_FragColor = vec4(f, f, f, 1.0);
}
