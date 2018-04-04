#ifdef GL_ES
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float outlineSize;
uniform vec3 outlineColor;
uniform vec2 textureSize;

const float PI = 0.01745329252;


int getIsStrokeWithAngel(float angel)
{
    int stroke = 0;
    float rad = angel * PI;
    vec2 unit = 1.0 / textureSize.xy;
    vec2 offset = vec2(outlineSize * cos(rad) * unit.x, outlineSize * sin(rad) * unit.y);
    float a = texture2D(u_texture, v_texCoords + offset).a;
    if (a >= 0.5)
    {
        stroke = 1;
    }
    return stroke;
}

void main()
{
    vec4 myC = texture2D(u_texture, v_texCoords);
    if (myC.a >= 0.5)
    {
        gl_FragColor = v_color * myC;
        return;
    }

    int strokeCount = 0;
    strokeCount += getIsStrokeWithAngel(0.0);
    strokeCount += getIsStrokeWithAngel(30.0);
    strokeCount += getIsStrokeWithAngel(60.0);
    strokeCount += getIsStrokeWithAngel(90.0);
    strokeCount += getIsStrokeWithAngel(120.0);
    strokeCount += getIsStrokeWithAngel(150.0);
    strokeCount += getIsStrokeWithAngel(180.0);
    strokeCount += getIsStrokeWithAngel(210.0);
    strokeCount += getIsStrokeWithAngel(240.0);
    strokeCount += getIsStrokeWithAngel(270.0);
    strokeCount += getIsStrokeWithAngel(300.0);
    strokeCount += getIsStrokeWithAngel(330.0);

    if (strokeCount > 0)
    {
        myC.rgb = outlineColor;
        myC.a = 1.0;
    }

    gl_FragColor = v_color * myC;
}