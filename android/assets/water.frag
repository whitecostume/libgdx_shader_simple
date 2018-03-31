#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;


uniform sampler2D u_texture;
uniform float time;
uniform vec2 resolution;


const float PI = 3.1415;
// 速度
const float speed = 0.2;
const float speed_x = 0.3;
const float speed_y = 0.3;

// 折射角
const float emboss = 0.3; 		// 凹凸强度
const float intensity = 2.4;	// 强度
const int steps = 8;  			// 波纹密度
const float frequency = 4.0;  	// 频率
const float angle = 7.0;

const float delta = 50.0;  		// 增幅（越小越激烈）
const float intence = 200.0;   	// 明暗强度

// 高光
const float reflectionCutOff = 0.012;
const float reflectionIntence = 80000.0;

float col(vec2 coord)
{
    float delta_theta = 2.0 * PI / angle;
    float col = 0.0;
    float theta = 0.0;
    for (int i = 0; i < steps; i++)
    {
        vec2 adjc = coord;
        theta = delta_theta * float(i);
        adjc.x += cos(theta)*time*speed + time * speed_x;
        adjc.y -= sin(theta)*time*speed - time * speed_y;
        col = col + cos((adjc.x * cos(theta) -
            adjc.y * sin(theta)) * frequency) * intensity;
    }
    return cos(col);
}


void main()
{
    vec2 p = v_texCoords, c1 = p, c2 = p;
    float cc1 = col(c1);

    c2.x += resolution.x/delta;
    float dx = emboss*(cc1-col(c2))/delta;

    c2.x = p.x;
    c2.y += resolution.y/delta;
    float dy = emboss*(cc1-col(c2))/delta;
    c1.x = c1.x +dx;
    c1.y =  c1.y+dy;

    float alpha = 1.0+dot(dx,dy)*intence;


    vec4 col = texture2D(u_texture,c1);
    col *= alpha;
    gl_FragColor =  col;
}