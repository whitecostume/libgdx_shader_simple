#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform vec2 shadowOffset;

uniform float shadowOpacity;

vec4 composite(vec4 over, vec4 under)
{
    return over + (1.0 - over.a)*under;
}


void main(){
    vec4 textureColor = texture2D(u_texture, v_texCoords);
    float shadowMask = texture2D(u_texture, v_texCoords +shadowOffset ).a;
    
    vec4 shadowColor = vec4(0,0,0,shadowMask *shadowOpacity);
    gl_FragColor = composite(textureColor, shadowColor);
}