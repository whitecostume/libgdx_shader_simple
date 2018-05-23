#ifdef GL_ES
precision lowp float;
#endif

uniform float time;

uniform sampler2D u_texture;
uniform sampler2D u_noise;

varying vec2 v_texCoords;
varying vec4 v_color;
void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    // 纹理坐标
    vec2 uv = v_texCoords;
    // 法向纹理r通道
    float height = texture2D(u_noise,uv).r;
    // 采样纹理
    vec4 color = v_color * texture2D(u_texture,uv);

    if(height < time)
    {
        discard;
    }

    if(height < time+0.04)
    {
        // 溶解颜色，可以自定义
        color = vec4(.9,.6,.3,color.a);
    }

    fragColor = color;
}

void main()
{
    mainImage(gl_FragColor, gl_FragCoord.xy);
}