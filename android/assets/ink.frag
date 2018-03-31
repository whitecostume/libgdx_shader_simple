#ifdef GL_ES
precision mediump float;
#endif
varying vec2 v_texCoords;
varying vec4 v_color;
uniform sampler2D u_texture;
uniform float m_fSpotLightRadius;
uniform vec2 resolution;
uniform vec2 orginPos;

const float M_PI = 3.1415;

float random(vec2 co){
    return fract(sin(normalize(co.x)) * 43758.5453);
}

void main() {


    float ranDis = random(v_texCoords);

    float a = 1.0;
    float j = v_texCoords.x * resolution.x;
    float k =   v_texCoords.y * resolution.y;
    float dis = distance(vec2(j, k), orginPos)*ranDis;

    if(dis < m_fSpotLightRadius){
        a = 0.0;
    }
    vec4 v = texture2D(u_texture, v_texCoords);

    gl_FragColor = v * v_color * a ;
}
