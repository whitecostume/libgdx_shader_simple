#ifdef GL_ES
precision mediump float;
#endif

varying vec2 v_texCoords;
varying vec4 v_color;
uniform sampler2D u_texture;
uniform vec2 u_lightPosition;  
uniform vec4 u_lightColor;  
uniform float u_lightRange;

vec4 getRenderColor(vec2 texPos, vec2 lightPos, float lightRange)
{  
    vec2 pos = texPos - lightPos;  
    float d = length(pos);//顶点与灯的距离

    float rgb = (d)/(lightRange);
    rgb = 1.0 - clamp(rgb, 0.0, 1.0);//clamp意义为 min(max(a, b), c);将a的大小限制在b,c之间， 1-rgb是将颜色反转  
    return vec4(rgb, rgb, rgb, 1.0);
}  
void main()  
{
    vec4 v = texture2D(u_texture, v_texCoords);
    vec4 color =u_lightColor * getRenderColor(v_texCoords, u_lightPosition, u_lightRange);//灯光颜色与灯光强度混合
    color = clamp(color, 0.0, 1.0);  
    gl_FragColor = v * color * v_color ;//纹理与灯光混合
}  